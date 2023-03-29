package ldap.learn;

import org.camunda.bpm.model.bpmn.Bpmn;
import org.camunda.bpm.model.bpmn.BpmnModelInstance;
import org.camunda.bpm.model.bpmn.instance.MessageFlow;
import org.camunda.bpm.model.bpmn.instance.Participant;
import org.camunda.bpm.model.bpmn.instance.Process;
import org.camunda.bpm.model.xml.instance.ModelElementInstance;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

public class BpmnFlowListerGUI {

	static ArrayList<Flux> fluxBpmn = new ArrayList<>();

	public static ArrayList<Flux> getFluxBpmn() {
		return fluxBpmn;
	}



	public static void main(String[] args) {

		try {
			File emprunt3 = new File("src/main/java/ldap/learn/essai3.bpmn");
			importBpmnFile(emprunt3);
		}catch(Exception e) {
			e.printStackTrace();

		}
	}




	public static void importBpmnFile(File file) {
		//System.out.println("hello");
		try {
			BpmnModelInstance bpmnModel = Bpmn.readModelFromFile(file);
			Process mainProcess = bpmnModel.getModelElementsByType(Process.class).iterator().next();

			// Récupération des flux de message
			Collection<MessageFlow> messageFlows = bpmnModel.getModelElementsByType(MessageFlow.class);


			Collection<Participant> participants = bpmnModel.getModelElementsByType(Participant.class);
			ArrayList<String> poolNames = new ArrayList<>();
			for (Participant participant : participants) {
				//System.out.println(participant.getName());
			}


			//System.out.println(poolNames);
			// Affichage des flux de message dans la console
			for (MessageFlow messageFlow : messageFlows) {

            	Participant source = getParticipant(messageFlow,participants).get(1);
				Participant target = getParticipant(messageFlow,participants).get(0);
                fluxBpmn.add(new Flux(source.getName(),target.getName(),messageFlow.getName()));
			}
			//System.out.println(fluxBpmn);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static ArrayList<Participant> getParticipant(MessageFlow messageFlow, Collection<Participant> participants) {
		// Récupérer l'élément Process cible du MessageFlow
		ModelElementInstance processTarget = messageFlow.getTarget().getParentElement();
		ModelElementInstance processSource = messageFlow.getSource().getParentElement();

		ArrayList<Participant> results = new ArrayList<Participant>();


		// Parcourir la collection de participants pour trouver celui qui correspond à l'élément Process
		for (Participant participant : participants) {
			if (participant.getProcess() != null && participant.getProcess().equals(processTarget)) {
				results.add(participant);
			}
		}

		for (Participant participant : participants) {
			if (participant.getProcess() != null && participant.getProcess().equals(processSource)) {
				results.add(participant);
			}
		}

		// Aucun Participant ne correspond à l'élément Process
		return results;
	}






}