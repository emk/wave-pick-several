package net.randomhacks.wave.voting.approval;

import com.google.gwt.core.ext.LinkerContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.core.ext.linker.AbstractLinker;
import com.google.gwt.core.ext.linker.Artifact;
import com.google.gwt.core.ext.linker.ArtifactSet;
import com.google.gwt.core.ext.linker.LinkerOrder;
import com.google.gwt.core.ext.linker.SyntheticArtifact;

@LinkerOrder(LinkerOrder.Order.POST)
public class ApprovalVotingLinker extends AbstractLinker {
	private static final String OLD_GADGET_XML = "net.randomhacks.wave.voting.approval.client.ApprovalVoting.gadget.xml";
	private static final String NEW_GADGET_XML = "pickseveral.gadget.xml";

	@Override
	public String getDescription() {
		return "ApprovalVoting custom linker";
	}

	@Override
	public ArtifactSet link(TreeLogger logger, LinkerContext context,
			ArtifactSet artifacts) throws UnableToCompleteException {
		ArtifactSet results = new ArtifactSet(artifacts);
		renameSyntheticArtifact(logger, results, OLD_GADGET_XML,
				NEW_GADGET_XML);
		return results;
	}

	private void renameSyntheticArtifact(TreeLogger logger,
			ArtifactSet results, String sourcePartialPath,
			String destPartialPath) throws UnableToCompleteException {
		SyntheticArtifact artifact =
			findSyntheticArtifact(results, sourcePartialPath);
		if (artifact == null)
			throw new IllegalArgumentException("Cannot find " + sourcePartialPath);
		renameSyntheticArtifact(logger, results, artifact, destPartialPath);
	}

	private void renameSyntheticArtifact(TreeLogger logger, ArtifactSet result,
			SyntheticArtifact gadgetXml, String partialPath)
			throws UnableToCompleteException {
		result.remove(gadgetXml);
		result.add(emitInputStream(logger, gadgetXml.getContents(logger), partialPath, gadgetXml.getLastModified()));
	}

	private SyntheticArtifact findSyntheticArtifact(ArtifactSet artifacts,
			String partialPath) {
		SyntheticArtifact result = null;
		for (Artifact<?> artifact : artifacts) {
			if (artifact instanceof SyntheticArtifact) {
				SyntheticArtifact synthetic = (SyntheticArtifact) artifact;
				if (synthetic.getPartialPath().equals(partialPath)) {
					result = synthetic;
				}
			}
		}
		return result;
	}
}
