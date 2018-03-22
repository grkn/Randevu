package com.hizliyol.core.rest.modal;

import java.util.List;

public class ModelEntity {
	private String value;
	private List<String> expressions;
	private String expression;
	private String intent;
	private String message;
	private List<CarouselItem> obj;
	private List<QuickReply> quickReply;
	private ListTemplate listTemplate;
	private List<GenericButton> genericButtons;
	private List<Attachment> attachments;
	private FacebookDeployment facebookDeployment;
	private String witDeployment;
	private String emoji;
	private EmojiModal source;
	private EmojiModal target;
	private String text;
	private String subject;
	private WitaiApp application;
	private SubjectFallback fallback;
	private List<PersistentMenu> persistentMenuList;
	private String chatbaseDeployment;
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public List<String> getExpressions() {
		return expressions;
	}
	public void setExpressions(List<String> expressions) {
		this.expressions = expressions;
	}
	public String getExpression() {
		return expression;
	}
	public void setExpression(String expression) {
		this.expression = expression;
	}
	public String getIntent() {
		return intent;
	}
	public void setIntent(String intent) {
		this.intent = intent;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public List<CarouselItem> getObj() {
		return obj;
	}
	public void setObj(List<CarouselItem> obj) {
		this.obj = obj;
	}
	public List<QuickReply> getQuickReply() {
		return quickReply;
	}
	public void setQuickReply(List<QuickReply> quickReply) {
		this.quickReply = quickReply;
	}
	public ListTemplate getListTemplate() {
		return listTemplate;
	}
	public void setListTemplate(ListTemplate listTemplate) {
		this.listTemplate = listTemplate;
	}
	public List<GenericButton> getGenericButtons() {
		return genericButtons;
	}
	public void setGenericButtons(List<GenericButton> genericButtons) {
		this.genericButtons = genericButtons;
	}
	public List<Attachment> getAttachments() {
		return attachments;
	}
	public void setAttachments(List<Attachment> attachments) {
		this.attachments = attachments;
	}
	public FacebookDeployment getFacebookDeployment() {
		return facebookDeployment;
	}
	public void setFacebookDeployment(FacebookDeployment facebookDeployment) {
		this.facebookDeployment = facebookDeployment;
	}
	public String getWitDeployment() {
		return witDeployment;
	}
	public void setWitDeployment(String witDeployment) {
		this.witDeployment = witDeployment;
	}
	public String getEmoji() {
		return emoji;
	}
	public void setEmoji(String emoji) {
		this.emoji = emoji;
	}
	public EmojiModal getSource() {
		return source;
	}
	public void setSource(EmojiModal source) {
		this.source = source;
	}
	public EmojiModal getTarget() {
		return target;
	}
	public void setTarget(EmojiModal target) {
		this.target = target;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public WitaiApp getApplication() {
		return application;
	}
	public void setApplication(WitaiApp application) {
		this.application = application;
	}
	public SubjectFallback getFallback() {
		return fallback;
	}
	public void setFallback(SubjectFallback fallback) {
		this.fallback = fallback;
	}
	public List<PersistentMenu> getPersistentMenuList() {
		return persistentMenuList;
	}
	public void setPersistentMenuList(List<PersistentMenu> persistentMenuList) {
		this.persistentMenuList = persistentMenuList;
	}
	public String getChatbaseDeployment() {
		return chatbaseDeployment;
	}
	public void setChatbaseDeployment(String chatbaseDeployment) {
		this.chatbaseDeployment = chatbaseDeployment;
	}

}
