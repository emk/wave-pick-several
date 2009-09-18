package net.randomhacks.wave.voting.approval.client;

import java.util.ArrayList;

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

	public void notifyChoicesChanged(ArrayList<Choice> choicesModel) {
		updateChoicesTableHeader(choicesModel);
		updateChoicesTableBody(choicesModel);
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

	private void updateChoicesTableBody(ArrayList<Choice> choicesModel) {
		int i = 0;
		for (Choice choice : choicesModel) {
			final String name = choice.name;
			int row = i + 1;
			if (i >= knownChoices.size()
					|| name.compareToIgnoreCase(knownChoices.get(i)) != 0) {
				knownChoices.add(i, name);
				insertChoiceRow(row, name);
			}
			updateChoiceRow(row, choice);
			++i;
		}
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

	private void updateChoiceRow(int row, Choice choice) {
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
	}

	public void setDynamicHeightFeature(DynamicHeightFeature feature) {
		dynamicHeightFeature = feature;
		dynamicHeightFeature.adjustHeight();
	}
}