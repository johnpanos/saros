package de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.conditions;

import org.eclipse.swtbot.swt.finder.waits.DefaultCondition;

import de.fu_berlin.inf.dpp.stf.server.rmiSwtbot.eclipse.workbench.EditorComponent;

public class IsFileContentsSame extends DefaultCondition {

    private EditorComponent state;

    private String[] fileNodes;
    private String otherClassContent;

    IsFileContentsSame(EditorComponent state, String otherClassContent,
        String... fileNodes) {
        this.state = state;
        this.fileNodes = fileNodes;
        this.otherClassContent = otherClassContent;

    }

    public String getFailureMessage() {
        return null;
    }

    public boolean test() throws Exception {
        String classContent = state.getFileContent(fileNodes);
        return classContent.equals(otherClassContent);
    }
}
