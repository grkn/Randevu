package com.hizliyol.core.rest.modal;

import java.util.List;

public class QuickReply {

	private String text;

	private List<Button> buttons;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<Button> getButtons() {
		return buttons;
	}

	public void setButtons(List<Button> buttons) {
		this.buttons = buttons;
	}
	
}
