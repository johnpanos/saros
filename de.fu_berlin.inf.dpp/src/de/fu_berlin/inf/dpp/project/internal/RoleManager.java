package de.fu_berlin.inf.dpp.project.internal;

import java.util.LinkedList;
import java.util.List;

import org.picocontainer.annotations.Inject;

import de.fu_berlin.inf.dpp.Saros;
import de.fu_berlin.inf.dpp.User;
import de.fu_berlin.inf.dpp.User.UserRole;
import de.fu_berlin.inf.dpp.activities.business.IActivity;
import de.fu_berlin.inf.dpp.activities.business.RoleActivity;
import de.fu_berlin.inf.dpp.annotations.Component;
import de.fu_berlin.inf.dpp.project.AbstractSarosSessionListener;
import de.fu_berlin.inf.dpp.project.AbstractSharedProjectListener;
import de.fu_berlin.inf.dpp.project.IActivityListener;
import de.fu_berlin.inf.dpp.project.IActivityProvider;
import de.fu_berlin.inf.dpp.project.ISarosSession;
import de.fu_berlin.inf.dpp.project.ISarosSessionListener;
import de.fu_berlin.inf.dpp.project.ISharedProjectListener;
import de.fu_berlin.inf.dpp.project.SarosSessionManager;
import de.fu_berlin.inf.dpp.ui.SessionView;

/**
 * This manager is responsible for handling driver changes.
 * 
 * @author rdjemili
 */
@Component(module = "core")
public class RoleManager implements IActivityProvider {

    private final List<IActivityListener> activityListeners = new LinkedList<IActivityListener>();

    private ISarosSession sarosSession;

    private ISharedProjectListener sharedProjectListener = new AbstractSharedProjectListener() {

        @Override
        public void roleChanged(User user) {

            /*
             * Not nice to have GUI stuff here, but it can't be handled in
             * SessionView because it is not guaranteed there actually is a
             * session view open.
             */
            SessionView.showNotification("Role changed", String.format(
                "%s %s now %s of this session.", user.getHumanReadableName(),
                user.isLocal() ? "are" : "is", user.isDriver() ? "a driver"
                    : "an observer"));
        }

        @Override
        public void userJoined(User user) {
            SessionView.showNotification("User joined",
                user.getHumanReadableName() + " joined the session.");
        }

        @Override
        public void userLeft(User user) {
            SessionView.showNotification("User left",
                user.getHumanReadableName() + " left the session.");
        }
    };

    @Inject
    protected Saros saros;

    public RoleManager(SarosSessionManager sessionManager) {
        sessionManager.addSarosSessionListener(sessionListener);
    }

    public final ISarosSessionListener sessionListener = new AbstractSarosSessionListener() {

        @Override
        public void sessionStarted(ISarosSession newSarosSession) {
            sarosSession = newSarosSession;
            sarosSession.addListener(sharedProjectListener);
            sarosSession.addActivityProvider(RoleManager.this);
        }

        @Override
        public void sessionEnded(ISarosSession oldSarosSession) {
            assert sarosSession == oldSarosSession;
            sarosSession.removeListener(sharedProjectListener);
            sarosSession.removeActivityProvider(RoleManager.this);
            sarosSession = null;
        }
    };

    /*
     * (non-Javadoc)
     * 
     * @see de.fu_berlin.inf.dpp.IActivityProvider
     */
    public void addActivityListener(IActivityListener listener) {
        this.activityListeners.add(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.fu_berlin.inf.dpp.IActivityProvider
     */
    public void removeActivityListener(IActivityListener listener) {
        this.activityListeners.remove(listener);
    }

    /*
     * (non-Javadoc)
     * 
     * @see de.fu_berlin.inf.dpp.IActivityProvider
     */
    public void exec(IActivity activity) {
        if (activity instanceof RoleActivity) {
            RoleActivity roleActivity = (RoleActivity) activity;
            User user = roleActivity.getAffectedUser();
            if (!user.isInSarosSession()) {
                throw new IllegalArgumentException("User " + user
                    + " is not a participant in this shared project");
            }
            UserRole role = roleActivity.getRole();
            this.sarosSession.setUserRole(user, role);
        }
    }
}
