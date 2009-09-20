package net.randomhacks.wave.voting.approval.client;

import java.util.ArrayList;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.gadgets.client.DynamicHeightFeature;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextBox;

public class ChoicesTable extends FlexTable implements ChoicesModel.Listener {
	private TextBox inputBox = new TextBox();
	private Button addButton = new Button("Add");
	
	private ChoicesModel model;
	private ArrayList<String> knownChoices = new ArrayList<String>();
	private DynamicHeightFeature dynamicHeightFeature;

	ChoicesTable(ChoicesModel model) {
		super();
		this.model = model;
		
		getRowFormatter().addStyleName(0, "choicesHeader");
		// We'll call setHeaderText later.
		
		HorizontalPanel hbox = new HorizontalPanel();
		hbox.add(inputBox);
		hbox.add(addButton);
		setWidget(1, 0, hbox);

		addEventHandlers();
		model.addListener(this);
	}
	
	private void addEventHandlers() {
		addButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				addChoice();
			}
		});
		inputBox.addKeyPressHandler(new KeyPressHandler() {
			public void onKeyPress(KeyPressEvent event) {
				if (event.getCharCode() == KeyCodes.KEY_ENTER)
					addChoice();
			}
		});
	}

	private void addChoice() {
		String input = inputBox.getText().trim();
		if (input.length() > 0) {
			inputBox.setText("");
			model.addChoice(input);
		}
	}

	public void notifyChoicesChanged(ArrayList<Choice> choicesModel,
			boolean isWritable) {
		updateChoicesTableHeader(choicesModel);
		updateChoicesTableBody(choicesModel, isWritable);
		updateChoicesTableFooter(isWritable);
		if (dynamicHeightFeature != null)
			dynamicHeightFeature.adjustHeight();
	}

	private void updateChoicesTableHeader(ArrayList<Choice> choicesModel) {
		if (choicesModel.size() < 2)
			setHeaderText("Add Choices");
		else
			setHeaderText("Pick Several");
	}

	private void setHeaderText(String headerText) {
		setText(0, 0, headerText);
	}

	private void updateChoicesTableBody(ArrayList<Choice> choicesModel,
			boolean isWritable) {
		Log.debug("Known before: " + knownChoices.toString());
		Log.debug("Model: " + choicesModel.toString());
		int i = 0;
		for (Choice choice : choicesModel) {
			int row = i + 1;
			
			// If 'choice.key' is alphabetically greater than the current
			// row, then we need to remove it. Normally, this can only
			// happen when rewinding playback in our gadget.
			while (i < knownChoices.size() &&
					choice.key.compareTo(knownChoices.get(i)) > 0) {
				// We have row in the table that has since been deleted.
				// This can only happen when rewinding playback.
				Log.debug("Removing row: " + knownChoices.get(i));
				knownChoices.remove(i);
				removeRow(row);
			}

			if (i >= knownChoices.size()
					|| choice.key.compareTo(knownChoices.get(i)) < 0) {
				Log.debug("Inserting row: " + choice.key);
				knownChoices.add(i, choice.key);
				insertChoiceRow(row, choice.name);
			}
			
			Log.debug("Updating row: " + choice.key);
			updateChoiceRow(row, choice, isWritable);
			++i;
		}
		while (i < knownChoices.size()) {
			int row = i + 1;
			Log.debug("Removing trailing row: " + knownChoices.get(i));
			knownChoices.remove(i);
			removeRow(row);
		}
		Log.debug("Known after: " + knownChoices.toString());
	}

	private void insertChoiceRow(int row, final String name) {
		insertRow(row);
		final CheckBox checkBox = new CheckBox(name);
		setWidget(row, 0, checkBox);
		checkBox.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
			public void onValueChange(ValueChangeEvent<Boolean> event) {
				model.setChosen(name, event.getValue());
			}
		});
	}

	private void updateChoiceRow(int row, Choice choice, boolean isWritable) {
		CheckBox checkBox = (CheckBox) getWidget(row, 0);
		checkBox.setValue(choice.wasChosenByMe);
		String label = choice.name;
		if (choice.votes > 0)
			label += " (" + Integer.toString(choice.votes) + ")";
		checkBox.setText(label);
		if (choice.isWinner)
			checkBox.addStyleName("winningChoice");
		else
			checkBox.removeStyleName("winningChoice");
		checkBox.setEnabled(isWritable);
	}

	private void updateChoicesTableFooter(boolean isWritable) {
		inputBox.setEnabled(isWritable);
		addButton.setEnabled(isWritable);
	}

	public void setDynamicHeightFeature(DynamicHeightFeature feature) {
		dynamicHeightFeature = feature;
		dynamicHeightFeature.adjustHeight();
	}
}
