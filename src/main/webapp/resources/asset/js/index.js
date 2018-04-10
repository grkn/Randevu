Vue.component('i18n_custom', {
	template : '<select v-model="lang" v-on:change="changeLanguage">'
					+'<option value="en">{{ $t("message.english") }}</option>'
					+'<option value="fr">{{ $t("message.french") }}</option>'
					+'<option value="tr">{{ $t("message.turkish") }}</option>'
				+'</select>',
	methods : {
		changeLanguage : function(){
			window.localStorage.setItem("lang", this.lang);
			window.location.reload();
		}
	},
	data : function() {
		return {lang : window.localStorage.getItem('lang')};
	}
});

Vue.component("routerLinkComponent", {
	template : '<div id="navbar"><div class="navbar-nav"><ul class="nav navbar-nav">'
				+'<li><router-link :to="{ name: \'home\'}">{{$t("message.intent")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'answersContainer\'}">{{$t("message.answers")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'subjectContainer\'}">{{$t("message.subject")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'trainingContainer\'}">{{$t("message.training")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'emojiContainer\'}">{{$t("message.emoji")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'configsContainer\'}">{{$t("message.configs")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'webChatContainer\'}">{{$t("message.webChat")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'facebookContainer\'}">{{$t("message.facebook")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'witContainer\'}">{{$t("message.witai")}}</router-link></li>'
				+'<li><router-link :to="{ name: \'chatbaseContainer\'}">{{$t("message.chatbase")}}</router-link></li>'
			+'</ul></div></div>'
}); 


if(!window.localStorage.getItem("lang"))
		window.localStorage.setItem("lang", "tr");

var i18n = new VueI18n({
  locale: window.localStorage.getItem("lang"), // set locale
  messages:messages // set locale messages
});


// Carousel popup
Vue.component('carousel_popup', {
	template : '<div id="myModalCarousel" class="modal fade" role="dialog">'
				+'<div class="modal-dialog">'
				    +'<div class="modal-content">'
				      +'<div class="modal-header fancyback">'
				        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
				        +'<h4 class="modal-title">{{$t("message.carousel")}}</h4>'
				      +'</div>'
				      +'<div class="modal-body">'			      
			      		+'<div><span><b>{{$t("message.selectIntent")}}</b></span>&nbsp;&nbsp;'
							+'<select v-model="selectedIntent" v-on:change="selectedIntentFunc"><optgroup v-for="intentList in list">'
								+'<option v-for="intent in intentList" v-bind:value="intent.value">{{intent.value}}</option></optgroup>'
							+'</select>'
						+'</div>'
						+'<br/>'
						+'<div>'
							+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeInputFields()">{{$t("message.removeCarousel")}}</button>'
							+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementInputFields()">{{$t("message.addCarousel")}}</button>'	
							+'<br/><br/>'
							+'<div v-for="car in carousel">'
								+'<table style="width:100%;">'
									+'<tr><td><label>{{$t("message.image_url")}} *</label></td><td><input type="text" v-model="car.imgUrl"/></td></tr>'
									+'<tr><td><label>{{$t("message.title")}} *</label></td><td><input type="text" v-model="car.title"/></td></tr>'
									+'<tr><td><label>{{$t("message.subtitle")}} *</label></td><td><input type="text" v-model="car.subtitle"/></td></tr>'
								+'</table>'
								+'<br/>'
								+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeButtons(car)">{{$t("message.removeButton")}}</button>'
								+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementButtons(car)">{{$t("message.addButton")}}</button>'
								+'<br/><br/>'
								+'<div v-for="button in car.buttons">'
									+'<table style="width:100%">'
										+'<tr><td><label>{{$t("message.url")}}</label></td><td><input type="text" v-model="button.url"/></td></tr>'
										+'<tr><td><label>{{$t("message.bName")}} *</label></td><td><input type="text" v-model="button.name"/></td></tr>'
										+'<tr><td><label>{{$t("message.text")}}</label></td><td><input type="text" v-model="button.text"/></td></tr>'
									+'</table>'
									+'<br/>'
								+'</div><!--button in-->'
								+'<hr/>'
							+'</div><!--car in-->'
						+'</div>'
					  +'</div><!--modal-body-->'
					  +'<div class="modal-footer" style="border-top:0;">'
					   		+'<button type="button" class="btn btn-info" v-on:click="save">{{$t("message.save")}}</button>'
				      +'</div>'
				    +'</div><!--modal-content-->'
				+'</div><!--modal-dialog-->'
			+'</div><!--myModalCarousel-->',
	props : ['entityList'],
	methods : {
		incrementInputFields : function(){
			if(this.carousel.length < 4){
				this.carousel.push({buttons : [{}]});
			}
		},
		removeInputFields : function(){
			if(this.carousel.length > 1){
				this.carousel.splice(this.carousel.length - 1, 1);
			}
		},
		incrementButtons : function(car){
			if(car.buttons.length < 3){
				car.buttons.push({});
			}
		},
		removeButtons : function(car){
			if(car.buttons.length > 1){
				car.buttons.splice(car.buttons.length -1, 1)
			}
		},
		save : function(){
			for(var i = 0; i < this.carousel.length; i++){
				if(!this.carousel[i].imgUrl || !this.carousel[i].title || !this.carousel[i].subtitle){
					var lang = window.localStorage.getItem('lang');
					if(lang == "tr"){
						alert("Lütfen yıldızlı alanları doldurunuz.");
					}else if (lang == "fr"){
						alert("Veuillez remplir tous les champs obligatoires");
					}else{
						alert("Please fill all the requried fields.");
					}
					return;
				}
				for(var j = 0; j < this.carousel[i].buttons.length; j++){
					if( !this.carousel[i].buttons[j].name){
						var lang = window.localStorage.getItem('lang');
						if(lang == "tr"){
							alert("Lütfen yıldızlı alanları doldurunuz.");
						}else if (lang == "fr"){
							alert("Veuillez remplir tous les champs obligatoires");
						}else{
							alert("Please fill all the requried fields.");
						}
						return;
					}
				}
			}
			Vue.http.post(contextPath + "/secure/api/view/create/carousel", {obj : this.carousel, intent : this.selectedIntent}, function(resp){
				$("#myModalCarousel").modal('hide');
			});
		},
		selectedIntentFunc : function(){
			var carouselTemp = this.carousel;
			Vue.http.post(contextPath + "/secure/api/view/get/carousel", {intent : this.selectedIntent}, function(resp){
				if(resp.type && resp.type == 'carousel'){
					while(0 < carouselTemp.length){
						carouselTemp.splice(0, 1);
					}
					for(var i = 0; i < resp.value.length; i++){
						carouselTemp.push(resp.value[i]);
					}
				}else{
					while(0 < carouselTemp.length){
						carouselTemp.splice(0, 1);
					}
					carouselTemp.push({buttons : [{}]});
				}
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.list = this.entityList;
  	})
	},
	data :	function () {
		return {list : [], selectedIntent : "", carousel : [{buttons : [{}]}]}
	}
});

// Add carousel
Vue.component('createCarousel', {
	template :'<div style="display:inline-block; padding-right:1%;">'
					+'<button v-on:click="loadPopup" type="button" class="btn btn-info">{{$t("message.carousel")}}</button>'
					+'<carousel_popup v-bind:entityList="entityList"></carousel_popup>'
				+'</div>',
	props : ['entityList'],
	methods : {
		loadPopup : function(){
				$("#myModalCarousel").modal();
		}
	}
});


// list Template popup
Vue.component('listTemplate_popup', {
	template :'<div id="myModalListTemplate" class="modal fade" role="dialog">'
					+'<div class="modal-dialog">'
					    +'<div class="modal-content">'
						      +'<div class="modal-header fancyback">'
						        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
						        +'<h4 class="modal-title">{{$t("message.listTemplate")}}</h4>'
						      +'</div>'
						      +'<div class="modal-body">'
							        +'<div><span><b>{{$t("message.selectIntent")}}</b></span>&nbsp;&nbsp;'
										+'<select v-model="selectedIntent" v-on:change="selectedIntentFunc"><optgroup v-for="intentList in list">'
											+'<option v-for="intent in intentList" v-bind:value="intent.value">{{intent.value}}</option></optgroup>'
										+'</select>'
									+'</div>'
									+'<br/>'
									+'<div>'									
											+'<br/>'
											+'<table style="width:100%">'
												+'<tr><td><label>{{$t("message.viewMoreButtonUrl")}}</label></td><td><input type="text" v-model="listTemplate.viewMoreButtonUrl"/></td></tr>'
												+'<tr><td><label>{{$t("message.viewMoreButtonName")}}</label></td><td><input type="text" v-model="listTemplate.viewMoreButtonName"/></td></tr>'
												+'<br/>'
											+'</table>'
											+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeInputFields()">{{$t("message.removeListItem")}}</button>'
											+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementInputFields()">{{$t("message.addListItem")}}</button>'
											+'<br/><br/>'
											+'<div v-for="listTemp in listTemplate.list">'
												+'<table style="width:100%">'
													+'<tr><td><label>{{$t("message.image_url")}}</label></td><td><input type="text" v-model="listTemp.imgUrl"/></td></tr>'
													+'<tr><td><label>{{$t("message.title")}} *</label></td><td><input type="text" v-model="listTemp.title"/></td></tr>'
													+'<tr><td><label>{{$t("message.subtitle")}} *</label></td><td><input type="text" v-model="listTemp.subTitle"/></td></tr>'
												+'</table>'
												+'<!--<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeButtons(listTemp)">{{$t("message.removeButton")}}</button>-->'
												+'<!--<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementButtons(listTemp)">{{$t("message.addButton")}}</button>-->'
												+'<div v-for="button in listTemp.buttons">'
												+'<br/><br/>'
													+'<table style="width:100%">'
														+'<tr><td><label>{{$t("message.url")}}</label></td><td><input type="text" v-model="button.url"/></td></tr>'
														+'<tr><td><label>{{$t("message.bName")}}*</label></td><td><input type="text" v-model="button.name"/></td></tr>'
														+'<tr><td><label>{{$t("message.text")}}</label></td><td><input type="text" v-model="button.text"/></td></tr>'
													+'</table>'
													+'<br/>'
												+'</div><!--button in-->'
												+'<hr/>'
											+'</div><!--listTemp in-->'
									+'</div><!-- -->'
							  +'</div><!--modal-body-->'
						      +'<div class="modal-footer" style="border-top:0;">'
								+'<button type="button" class="btn btn-info" v-on:click="save">{{$t("message.save")}}</button>'
						      +'</div>'
					    +'</div><!--modal-content-->'
					+'</div><!--modal-dialog-->'
				+'</div><!--myModal-->',
	props : ['entityList'],
	methods : {
		incrementInputFields : function(){
			if(this.listTemplate.list.length < 4){
					this.listTemplate.list.push({buttons:[{}]});
			}
		},
		removeInputFields : function(){
			if(this.listTemplate.list.length > 1){
				this.listTemplate.list.splice(this.listTemplate.list.length - 1, 1);
			}
		},
		incrementButtons : function(listTemplate){
			if(listTemplate.list.buttons.length < 1){
				listTemplate.list.buttons.push({});
			}
		},
		removeButtons : function(listTemplate){
			if(listTemplate.list.buttons.length > 0){
				listTemplate.list.buttons.splice(listTemplate.list.buttons.length -1, 1)
			}
		},
		save : function(){
			if(!this.listTemplate.list || this.listTemplate.list.length < 2){
				var lang = window.localStorage.getItem('lang');
				if(lang == "tr"){
					alert("Lütfen en az iki tane eleman giriniz.");
				}else if(lang == "fr"){
					alert("Veuillez remplir au moins deux éléments.");
				}else{
					alert("Please fill at least two item.");
				}
				return;
			}
			for(var i = 0; i < this.listTemplate.list.length; i++){
				var lang = window.localStorage.getItem('lang');
				if(!this.listTemplate.list[i].title || !this.listTemplate.list[i].subTitle){
					if(lang == "tr"){
						alert("Lütfen yıldızlı alanları doldurunuz.");
					}else if (lang == "fr"){
						alert("Veuillez remplir tous les champs obligatoires");
					}else{
						alert("Please fill all the requried fields.");
					}
					return;
				}
				for(var j = 0 ; j < this.listTemplate.list[i].buttons.length;j++){
					if(!this.listTemplate.list[i].buttons[j].name){
						if(lang == "tr"){
							alert("Lütfen yıldızlı alanları doldurunuz.");
						}else if (lang == "fr"){
							alert("Veuillez remplir tous les champs obligatoires");
						}else{
							alert("Please fill all the requried fields.");
						}
						return;
					}
				}
			}
			
			Vue.http.post(contextPath + "/secure/api/view/create/listTemplate", {listTemplate : this.listTemplate, intent : this.selectedIntent}, function(resp){
				$("#myModalListTemplate").modal('hide');
			});
		},
		selectedIntentFunc : function(){
			var listTemplate = this.listTemplate;
			Vue.http.post(contextPath + "/secure/api/view/get/listTemplate", {intent : this.selectedIntent}, function(resp){
				if(resp.type && resp.type == 'listTemplate'){
					while(0 < listTemplate.list.length){
						listTemplate.list.splice(0, 1);
					}					
					listTemplate.viewMoreButtonUrl = resp.value.viewMoreButtonUrl;
					listTemplate.viewMoreButtonName = resp.value.viewMoreButtonName;
					for(var i = 0; i < resp.value.list.length; i++){
						listTemplate.list.push(resp.value.list[i]);
					}
				}else{
					while(0 < listTemplate.list.length){
						listTemplate.list.splice(0, 1);
					}
					listTemplate.list.push({buttons : [{}]});				
					listTemplate.viewMoreButtonUrl = "";
					listTemplate.viewMoreButtonName = "";
				}
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.list = this.entityList;
  	})
	},
	data :	function () {
		return {list : [], selectedIntent : "", listTemplate : {list : [{buttons : [{}]}]}}
	}
});

// Add list Template
Vue.component('createListTemplate', {
	template :'<div style="display:inline-block; padding-right:1%;">'
					+'<button v-on:click="loadPopup" type="button" class="btn btn-info">{{$t("message.listTemplate")}}</button>'
					+'<listTemplate_popup v-bind:entityList="entityList"></listTemplate_popup>'
				+'</div>',
	props : ['entityList'],
	methods : {
		loadPopup : function(){
				$("#myModalListTemplate").modal();
		}
	}
});


//Quick Reply popup
Vue.component('quickreply_popup', {
	template :'<div id="myModalquickreply" class="modal fade" role="dialog">'
					+'<div class="modal-dialog">'
					    +'<div class="modal-content">'
					      +'<div class="modal-header fancyback">'
					        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
					        +'<h4 class="modal-title">{{$t("message.quickReply")}}</h4>'
					      +'</div>'
					      +'<div class="modal-body">'
								+'<div><span><b>{{$t("message.selectIntent")}}</b></span>&nbsp;&nbsp;'
									 +'<select v-model="selectedIntent" v-on:change="selectedIntentFunc"><optgroup v-for="intentList in list">'
										 +'<option v-for="intent in intentList" v-bind:value="intent.value">{{intent.value}}</option></optgroup>'
									 +'</select>'
								 +'</div>'
								 +'<br/>'
								+'<div>'
									+'<div v-for="qReply in quickReply">'
										+'<table style="width:100%">'
											+'<tr><td><label>{{$t("message.content")}} *</label></td><td><input type="text" v-model="qReply.text"/></td></tr>'
										+'</table>'
										+'<br/>'
										+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeButtons(qReply)">{{$t("message.removeButton")}}</button>'
										+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementButtons(qReply)">{{$t("message.addButton")}}</button>'
										+'<br/><br/>'
										+'<div v-for="button in qReply.buttons">'
											+'<table style="width:100%">'
												+'<tr><td><label>{{$t("message.bName")}} *</label></td><td><input type="text" v-model="button.name"/></td></tr>'
												+'<tr><td><label>{{$t("message.text")}} *</label></td><td><input type="text" v-model="button.text"/></td></tr>'
											+'</table>'
											+'<br/>'
										+'</div><!--button in-->'
										+'<hr/>'
									+'</div><!--qReply in-->'
								+'</div><!-- -->'
						  +'</div><!--modal-body-->'
					      +'<div class="modal-footer" style="border-top:0;">'
								+'<button type="button" class="btn btn-info" v-on:click="save">{{$t("message.save")}}</button>'
					      +'</div>'
					    +'</div><!--modal-content-->'
				+'</div><!--modal-dialog-->'
				+'</div><!--myModal-->',
	props : ['entityList'],
	methods : {
		incrementButtons : function(quickReply){
			if(quickReply.buttons.length < 5){
				quickReply.buttons.push({});
			}
		},
		removeButtons : function(quickReply){
			if(quickReply.buttons.length > 1){
				quickReply.buttons.splice(quickReply.buttons.length -1, 1)
			}
		},
		save : function(){
			var buttons = this.quickReply[0].buttons;
			if(!this.quickReply[0].text){
				var lang = window.localStorage.getItem('lang');
				if(lang == "tr"){
					alert("Lütfen yıldızlı alanları doldurunuz.");
				}else if (lang == "fr"){
					alert("Veuillez remplir tous les champs obligatoires");
				}else{
					alert("Please fill all the requried fields.");
				}
			}
			for(var i = 0; i < buttons.length; i++){
				if(!buttons[i].name || !buttons[i].text){
					var lang = window.localStorage.getItem('lang');
					if(lang == "tr"){
						alert("Lütfen yıldızlı alanları doldurunuz.");
					}else if (lang == "fr"){
						alert("Veuillez remplir tous les champs obligatoires");
					}else{
						alert("Please fill all the requried fields.");
					}
					return;
				}
			}
			Vue.http.post(contextPath + "/secure/api/view/create/quickReply", {quickReply : this.quickReply, intent : this.selectedIntent}, function(resp){
				$("#myModalquickreply").modal('hide');
			});
		},
		selectedIntentFunc : function(){
			var quickReplyTemp = this.quickReply;
			Vue.http.post(contextPath + "/secure/api/view/get/quickReply", {intent : this.selectedIntent}, function(resp){
				if(resp.type && resp.type == 'quickReply'){
					while(0 < quickReplyTemp.length){
						quickReplyTemp.splice(0, 1);
					}
					for(var i = 0; i < resp.value.length; i++){
						quickReplyTemp.push(resp.value[i]);
					}
				}else{
					while(0 < quickReplyTemp.length){
						quickReplyTemp.splice(0, 1);
					}
					quickReplyTemp.push({buttons : [{}]});
				}
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.list = this.entityList;
	})
	},
	data :	function () {
		return {list : [], selectedIntent : "", quickReply : [{buttons : [{}]}]}
	}
});

//Add quick reply
Vue.component('createQuickReply', {
	template :'<div style="display:inline-block; padding-right:1%;">'
				+'<button v-on:click="loadPopup" type="button" class="btn btn-info">{{$t("message.quickReply")}}</button>'
				+'<quickreply_popup v-bind:entityList="entityList"></quickreply_popup>'
			+'</div>',
	props : ['entityList'],
	methods : {
		loadPopup : function(){
				$("#myModalquickreply").modal();
		}
	}
});


// Generic Buttons popup
Vue.component('generic_buttons_popup', {
	template :'<div id="myModalGenericButtons" class="modal fade" role="dialog">'
					+'<div class="modal-dialog">'
					    +'<div class="modal-content">'
					      +'<div class="modal-header fancyback">'
					        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
					        +'<h4 class="modal-title">{{$t("message.genericButtons")}}</h4>'
					      +'</div>'
					      +'<div class="modal-body">'
					        +'<div><span><b>{{$t("message.selectIntent")}}</b></span>&nbsp;&nbsp;'
								+'<select v-model="selectedIntent" v-on:change="selectedIntentFunc"><optgroup v-for="intentList in list">'
									+'<option v-for="intent in intentList" v-bind:value="intent.value">{{intent.value}}</option></optgroup>'
								+'</select>'
							+'</div>'
							+'<br/>'
							+'<div>'
								+'<div v-for="gButtons in genericButtons">'
									+'<table style="width:100%">'
										+'<tr><td><label>{{$t("message.content")}} *</label></td><td><input type="text" v-model="gButtons.text"/></td></tr>'
									+'</table>'
									+'<br/>'
									+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeButtons(gButtons)">{{$t("message.removeButton")}}</button>'
									+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementButtons(gButtons)">{{$t("message.addButton")}}</button>'
									+'<br/><br/>'
									+'<div v-for="button in gButtons.buttons">'
										+'<table style="width:100%">'
											+'<tr><td><label>{{$t("message.url")}}</label></td><td><input type="text" v-model="button.url"/></td></tr>'
											+'<tr><td><label>{{$t("message.bName")}} *</label></td><td><input type="text" v-model="button.name"/></td></tr>'
											+'<tr><td><label>{{$t("message.text")}} *</label></td><td><input type="text" v-model="button.text"/></td></tr>'
										+'</table>'
										+'<br/>'
									+'</div><!--button in-->'
									+'<hr/>'
								+'</div><!--gButtons in-->'
							+'</div><!-- -->'
						  +'</div><!--modal-body-->'
					      +'<div class="modal-footer" style="border-top:0;">'
								+'<button type="button" class="btn btn-info" v-on:click="save">{{$t("message.save")}}</button>'
					      +'</div>'
					    +'</div><!--modal-content-->'
					+'</div><!--modal-dialog-->'
				+'</div><!--myModal-->',
	props : ['entityList'],
	methods : {
		incrementInputFields : function(){
			if(this.genericButtons.length < 4){
					this.genericButtons.push({buttons : [{}]});
			}
		},
		removeInputFields : function(){
			if(this.genericButtons.length > 1){
				this.genericButtons.splice(this.genericButtons.length - 1, 1);
			}
		},
		incrementButtons : function(gButtons){
			if(gButtons.buttons.length < 3){
				gButtons.buttons.push({});
			}
		},
		removeButtons : function(gButtons){
			if(gButtons.buttons.length > 1){
				gButtons.buttons.splice(gButtons.buttons.length -1, 1)
			}
		},
		save : function(){
			var buttons = this.genericButtons[0].buttons;
			for(var i = 0;i < buttons.length; i++){
				if(!buttons[i].name || !buttons[i].text){
					var lang = window.localStorage.getItem('lang');
					if(lang == "tr"){
						alert("Lütfen yıldızlı alanları doldurunuz.");
					}else if (lang == "fr"){
						alert("Veuillez remplir tous les champs obligatoires");
					}else{
						alert("Please fill all the requried fields.");
					}
					return;
				}
			}
			Vue.http.post(contextPath + "/secure/api/view/create/genericButtons", {genericButtons : this.genericButtons, intent : this.selectedIntent}, function(resp){
				$("#myModalGenericButtons").modal('hide');
			});
		},
		selectedIntentFunc : function(){
			var genericButtons = this.genericButtons;
			Vue.http.post(contextPath + "/secure/api/view/get/genericButtons", {intent : this.selectedIntent}, function(resp){
				if(resp.type && resp.type == 'genericButtons'){
					while(0 < genericButtons.length){
						genericButtons.splice(0, 1);
					}
					for(var i = 0; i < resp.value.length; i++){
						genericButtons.push(resp.value[i]);
					}
				}else{
					while(0 < genericButtons.length){
						genericButtons.splice(0, 1);
					}
					genericButtons.push({buttons : [{}]});
				}
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.list = this.entityList;
  	})
	},
	data :	function () {
		return {list : [], selectedIntent : "", genericButtons : [{buttons : [{}]}]}
	}
});

// Add Generic Buttons
Vue.component('createGenericButtons', {
	template :'<div style="display:inline-block; padding-right:1%;">'
				+'<button v-on:click="loadPopup" type="button" class="btn btn-info">{{$t("message.genericButtons")}}</button>'
				+'<generic_buttons_popup v-bind:entityList="entityList"></generic_buttons_popup>'
			+'</div>',
	props : ['entityList'],
	methods : {
		loadPopup : function(){
				$("#myModalGenericButtons").modal();
		}
	}
});


// Attachment popup
Vue.component('attachment_popup', {
	template :'<div id="myModalAttachment" class="modal fade" role="dialog">'
					+'<div class="modal-dialog">'
					    +'<div class="modal-content">'
						      +'<div class="modal-header fancyback">'
						        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
						        +'<h4 class="modal-title">{{$t("message.attachment")}}</h4>'
						      +'</div>'
						      +'<div class="modal-body">'
						        +'<div><span><b>{{$t("message.selectIntent")}}</b></span>&nbsp;&nbsp;'
										+'<select v-model="selectedIntent" v-on:change="selectedIntentFunc"><optgroup v-for="intentList in list">'
											+'<option v-for="intent in intentList" v-bind:value="intent.value">{{intent.value}}</option></optgroup>'
										+'</select>'
								+'</div>'
								+'<br/>'
								+'<div>'
									+'<div v-for="atch in genericButtons">'
										
										+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="removeButtons(atch)">{{$t("message.removeAttachment")}}</button>'
										+'<button style="float:right;margin:3px;" class="btn btn-info" v-on:click="incrementButtons(atch)">{{$t("message.addAttachment")}}</button>'
										+'<br/><br/>'
										+'<div v-for="button in atch.buttons">'
											+'<table style="width:100%">'
												+'<tr><td><label>{{$t("message.fileURL")}} *</label></td><td><input type="text" v-model="button.url"/></td></tr>'
												+'<tr><td><label>{{$t("message.fileName")}} *</label></td><td><input type="text" v-model="button.name"/></td></tr>'
											+'</table>'
											+'<br/>'
										+'</div><!--button in-->'
										+'<hr/>'
									+'</div><!--atch in-->'
								+'</div><!-- -->'
							  +'</div><!--modal-body-->'
						      +'<div class="modal-footer" style="border-top:0;">'
									+'<button type="button" class="btn btn-info" v-on:click="save">{{$t("message.save")}}</button>'
						      +'</div>'
					    +'</div><!--modal-content-->'
					+'</div><!--modal-dialog-->'
				+'</div><!--myModal-->',
	props : ['entityList'],
	methods : {
		incrementInputFields : function(){
			if(this.genericButtons.length < 4){
					this.genericButtons.push({buttons : [{}]});
			}
		},
		removeInputFields : function(){
			if(this.genericButtons.length > 1){
				this.genericButtons.splice(this.genericButtons.length - 1, 1);
			}
		},
		incrementButtons : function(atch){
			if(atch.buttons.length < 3){
				atch.buttons.push({});
			}
		},
		removeButtons : function(atch){
			if(atch.buttons.length > 1){
				atch.buttons.splice(atch.buttons.length -1, 1)
			}
		},
		save : function(){
			var buttons = this.genericButtons[0].buttons;
			for(var i=0; i < buttons.length; i++){
				if(!buttons[i].url || !buttons[i].name){
					var lang = window.localStorage.getItem('lang');
					if(lang == "tr"){
						alert("Lütfen yıldızlı alanları doldurunuz.");
					}else if (lang == "fr"){
						alert("Veuillez remplir tous les champs obligatoires");
					}else{
						alert("Please fill all the requried fields.");
					}
					return;
				}
			}
			Vue.http.post(contextPath + "/secure/api/view/create/attachment", {attachments : this.genericButtons, intent : this.selectedIntent}, function(resp){
				$("#myModalAttachment").modal('hide');
			});
		},
		selectedIntentFunc : function(){
			var genericButtons = this.genericButtons;
			Vue.http.post(contextPath + "/secure/api/view/get/attachment", {intent : this.selectedIntent}, function(resp){
				if(resp.type && resp.type == 'attachment'){
					while(0 < genericButtons.length){
						genericButtons.splice(0, 1);
					}
					for(var i = 0; i < resp.value.length; i++){
						genericButtons.push(resp.value[i]);
					}
				}else{
					while(0 < genericButtons.length){
						genericButtons.splice(0, 1);
					}
					genericButtons.push({buttons : [{}]});
				}
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.list = this.entityList;
  	})
	},
	data :	function () {
		return {list : [], selectedIntent : "", genericButtons : [{buttons : [{}]}]}
	}
});

// Add Attachment
Vue.component('createAttachment', {
	template :'<div style="display:inline-block; padding-right:1%;">'
				+'<button v-on:click="loadPopup" type="button" class="btn btn-info">{{$t("message.attachment")}}</button>'
				+'<attachment_popup v-bind:entityList="entityList"></attachment_popup>'
			+'</div>',
	props : ['entityList'],
	methods : {
		loadPopup : function(){
				$("#myModalAttachment").modal();
		}
	}
});


//Intent silme, cumle ekleme, cumle silme, konu ekleme
Vue.component('intent', {
	template :'<div class="col-sm-6 col-md-4">'
				+'<div class="thumbnail niceMng">'
					+'<div class="caption">'
						+'<div><span style="float:right;cursor:pointer;" v-on:click="removeIntent">X</span></div>'
						+'<h3> {{value}} </h3>'	
						+'<hr/>'
						+'<p><label>{{$t("message.enterSentence")}}</label>'
						+'<input class="smallinput" type="text" v-model="sentence"></p>'						
						+'<p>'
							+'<a class="btn btn-info" style="margin:3%;margin-left:0;" role="button" v-on:click="addSentence(value)">{{$t("message.save")}}</a>'
						+'</p>'
						+'<p><label>{{$t("message.storedSentence")}}</label>'
						+'<select class="smallselect" v-model="expression"><option v-for="exp in expressions" v-bind:value="{ value: exp }">{{ exp }}</option></select>'
						+'</p>'
						+'<p>'
						+'<a class="btn btn-default" style="margin:3%;margin-left:0;" role="button" v-on:click="removeSentece(value)">{{$t("message.remove")}}</a>'
						+'</p>'						
						+'<p><label>{{$t("message.subject")}}</label>'
							+'<select class="smallselect" v-model="subject.subject"><option v-for="sub in subjectArray" v-bind:value="sub.subject">{{ sub.subject}}</option></select>'
						+'</p>'
						+'<p>'
							+'<a class="btn btn-info" style="margin:3%;margin-left:0;" role="button" v-on:click="saveSubject">{{$t("message.save")}}</a>'
							+'<a class="btn btn-default" role="button" v-on:click="removeSubject">{{$t("message.remove")}}</a>'
						+'</p>'
					+'</div>'
				+'</div>'
			+'</div>',
	props: ['value', 'index', 'expressions', 'subjectArray'],
	methods : {
		addSentence : function(id){
			if(this.sentence.trim() != ""){
				this.expressions.unshift(this.sentence);
				this.sentence = "";
				var exps = [];
				for(var i = 0; i < this.expressions.length; i++){
					exps.push(this.expressions[i]);
				}
				Vue.http.post(contextPath + "/secure/api/post/intent/expressions", {value : this.value, expressions:exps}).then(function(resp){
				});
			}
		},
		removeSentece : function(id){
			if(this.expression.value.trim() != ""){
				var index = this.expressions.indexOf(this.expression.value);
				this.expressions.splice(index,1);
				Vue.http.delete(contextPath + "/secure/api/delete/intent/expressions", {value : this.value, expression : this.expression.value}).then(function(resp){
				});
			}
		},
		removeIntent : function(){
			Vue.http.delete(contextPath + "/secure/api/delete/intent", {value : this.value}).then(function(resp){
				window.location.reload();
			});
		},
		saveSubject : function(){
			if(this.subject && this.subject.subject)
			Vue.http.post(contextPath + "/secure/api/mongo/post/subjectRelation", {subject : this.subject.subject, intent : this.value}).then(function(resp){
				var lang = window.localStorage.getItem('lang');
				if(lang == "tr"){
					alert("Seçtiğiniz konu kaydedilmiştir.");
				}else if (lang == "fr"){
					alert("Le sujet sélectionné a été enregistré.");
				}else{
					alert("Selected subject has been saved.");
				}
			});
		},
		removeSubject : function(){
			if(this.subject && this.subject.subject)
			Vue.http.delete(contextPath + "/secure/api/mongo/delete/subjectRelation", {subject : this.subject.subject, intent : this.value}).then(function(resp){
				var lang = window.localStorage.getItem('lang');
				if(lang == "tr"){
					alert("Seçtiğiniz konu silinmiştir.");
				}else if (lang == "fr"){
					alert("Le sujet sélectionné a été supprimé.");
				}else{
					alert("Selected subject has been removed.");
				}
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			var subject = this.subject;
			Vue.http.get(contextPath + "/secure/api/mongo/get/subject/" + this.value).then(function(resp){
				if(resp.data[0]){
					subject.subject = resp.data[0].subject;
					subject.intent = resp.data[0].intent;
				}
			});
	  });
	},
	data :	function () {
		return {sentence : "", expression : {}, subject : {subject : "", intent : ""}}
	}
});

//Intent row template
Vue.component('row', {
	template : '<div class="row"><intent v-for="(intent,index) in array" v-bind:subjectArray="subjectArray" v-bind:value="intent.value" v-bind:expressions="intent.expressions" v-bind:index="index" :key="intent.value"></intent></div>',
	props: ['array','subjectArray']
	
});

//Intent Sayfasi
var container = Vue.component('container', {
	template:'<div class="container">'
					+'<div class="header">'
						+'<div class="page-header">'
							+'<div class="headersp">'
								+'<h1>{{$t("message.intentPage")}}</h1>'
							+'</div>'
							+'<routerLinkComponent></routerLinkComponent>'
							+'<span class="navbar-nav rootRight">'
								+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
							+'</span>'
							+'<span class="navbar-nav rootRight"">'
								+'<i18n_custom></i18n_custom>'
							+'</span>'							
						+'</div> <!--page-header-->'
					+'</div> <!--header-->'
					+'<div class="content">'
					+'<br/>'
						+'<div style="margin-left:20%;width:80%;margin-bottom:4%">'
							+'<div><label>{{$t("message.intentName")}} :</label>&nbsp;'
								+'<input type="text" v-model="intentName"/>&nbsp;&nbsp;'
								+'<label>{{$t("message.subject")}} :</label>&nbsp;&nbsp;'
								+'<select v-model="subject"><option v-for="subject in subjectList.value">{{subject.subject}}</option></select>&nbsp;&nbsp;'
								+'<button type="button" class="btn btn-info" v-on:click="createIntent">{{$t("message.create")}}</button>'
								+'<div class="search"><label>{{$t("message.search")}}&nbsp;&nbsp;</label><input type="text" v-model="searchText" v-on:keyup="search"/></div>'
							+'</div>'
						+'</div>'
						+'<div class="col-md-2 fancyLeftColumn">'
							+'<ul v-for="intent in this.original"><li v-for="i in intent"><span style="cursor:pointer;" v-on:click="showOnlyThisItem(i)">{{i.value}}</span></li></ul>'
						+'</div>'
						+'<div class="col-md-10">'
							+'<row v-for="intentArray in this.intentList" v-bind:array="intentArray" v-bind:subjectArray="subjectList.value"></row>'
					+'</div>'
				+'</div> <!--content-->'
			+'</div> <!--container-->',
	methods : {
		showOnlyThisItem : function(intent){
			this.intentList = [[intent]];
		},
		createIntent : function(){
			if(this.intentName.trim() != ""){
				Vue.http.post(contextPath + "/secure/api/create/intent", {value : this.intentName, subject : this.subject}).then(function(resp){
					window.location.reload();
				});
			}
		},
		search : function(){
			if(this.searchText.trim() == ""){
				this.immutableObjectToEntity();
				return;
			}
			this.immutableObjectToEntity();
			for(var i = 0; i < this.original.length; i++){
				for(var j = 0; j < this.original[i].length; j++){
					if(this.original[i][j].value.toLocaleUpperCase().indexOf(this.searchText.toLocaleUpperCase()) < 0){
						var k = 0;
						for(k = 0; k < this.intentList.length; k++){
							var z = 0;
							var flag = false;
							for(z = 0; z < this.intentList[k].length; z++){
								if(this.intentList[k][z].value == this.original[i][j].value){
									flag = true;
									break;
								}
							}
							if(flag){
								this.intentList[k].splice(z,1);
							}
						}
					}
				}
			}
		},
		immutableObjectToOriginal: function(){
			for(var i = 0; i < this.intentList.length; i++){
				var mod3Array = [];
				for(var j = 0; j < this.intentList[i].length; j++){
					var obj = {};
					for(var key in this.intentList[i][j]){
						obj[key] = this.intentList[i][j][key];
					}
					mod3Array = mod3Array.concat(obj);
				}
				this.original.push(mod3Array);
			}
		},
		immutableObjectToEntity: function(){
			this.intentList = [];
			for(var i = 0; i < this.original.length; i++){
				var mod3Array = [];
				for(var j = 0; j < this.original[i].length; j++){
					var obj = {};
					for(var key in this.original[i][j]){
						obj[key] = this.original[i][j][key];
					}
					mod3Array = mod3Array.concat(obj);
				}
				this.intentList.push(mod3Array);
			}
		},
		mountFunc : function(iList,func){
			Vue.http.get(contextPath + "/secure/api/get/witai/entities").then(function(resp){
			  var counter = 0;
				var index = -1;
				for(var i = 0; i < resp.data.values.length; i++){
					if(counter % 3 == 0){
						index++;
						iList[index] = [];
					}
					iList[index].push(resp.data.values[i]);
					counter++;
				}
				func();
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.mountFunc(this.intentList, this.immutableObjectToOriginal);
			var subjectList = this.subjectList;
			Vue.http.get(contextPath + "/secure/api/mongo/get/subjects").then(function(resp){
				 subjectList.value = resp.data;
			});
	  });
	},
	data :	function () {
		return {intentList :[], original :[],
		searchText : "", intentName : "", subject : "", subjectList : {value : []}}
	}
});


//Answers cevap ekleme
Vue.component('answers', {
	template :'<div class="col-sm-6 col-md-4">'
					+'<div class="thumbnail niceMng">'
				  	+'<div class="caption">'
							+'<h3> {{value}} </h3>'
							+'<hr/>'
							+'<p><label>{{$t("message.savedAnswer")}} </label>'
							+'<input style="width:100%;height:25px;" type="text" v-model="sentence.value"></p>'
							+'<label><span v-html="sentence.default"></span></label></p>'
							+'<p>'
								+'<a class="btn btn-info" style="margin:3px;" role="button" v-on:click="addAnswer(value)">{{$t("message.save")}}</a>'
								+'<a class="btn btn-default" style="margin:3px;" role="button" v-on:click="removeAnswer(value)">{{$t("message.remove")}}</a>'
							+'</p>'
					  +'</div>'
					+'</div>'
			  +'</div>',
	props: ['value', 'index', 'expressions'],
	methods : {
		addAnswer : function(id){
			if(this.sentence.value.trim() != ""){
				var sentence = this.sentence;
				Vue.http.post(contextPath + "/secure/api/send/meaningful/sentence", {intent : this.value, message : this.sentence.value}).then(function(resp){
						sentence.default = sentence.value;
						sentence.value = "";
				});
			}
		},
		removeAnswer : function(id){
			if(this.sentence.default.trim() != ""){
				var sentence = this.sentence;
				Vue.http.delete(contextPath + "/secure/api/delete/meaningful/sentence", {intent : this.value, message : this.sentence.default}).then(function(resp){
						sentence.default = "";
						sentence.value = "";
				});
			}
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			var sentence = this.sentence
			Vue.http.get(contextPath + "/secure/api/get/meaningful/sentence", {"intent" : this.value.toString()}).then(function(resp){
					if(resp.data.resp != "NOT_FOUND"){
						if(resp.data.type && resp.data.type == 'text'){
							sentence.default = resp.data.value;
						}else if(resp.data.type && resp.data.type == 'emoji'){
							sentence.default = resp.data.value;
						}else{
							sentence.default = resp.data.type;
						}
					}
			});
	  });
	},
	data :	function () {
		return {sentence : {value : "", default : ""}, expression : ""}
	}
});

// Answers row template
Vue.component('answersRow', {
	template :'<div class="row">'
					+'<answers v-for="(intent,index) in array" v-bind:value="intent.value" v-bind:expressions="intent.expressions" v-bind:index="index" :key="intent.value"></answers>'
				+'</div>',
	props: ['array']
});

// Answers Sayfasi
var answersContainer = Vue.component("answersContainer", {
	template:'<div class="container">'
				+'<div class="header">'
					+'<div class="page-header">'
						+'<div class="headersp">'
							+'<h1>{{$t("message.answerPage")}}</h1>'
						+'</div>'
						+'<routerLinkComponent></routerLinkComponent>'
						+'<span class="navbar-nav rootRight">'
							+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
						+'</span>'
						+'<span class="navbar-nav rootRight">'
							+'<i18n_custom></i18n_custom>'
						+'</span>'				
					+'</div> <!--page-header-->'
				+'</div> <!--header-->'
				+'<div class="content">'
				+'<br/>'
					+'<div style="margin-left:20%;width:80%;margin-bottom:4%">'
						+'<createCarousel v-bind:entityList="this.original"></createCarousel>'
						+'<createListTemplate v-bind:entityList="this.original"></createListTemplate>'
						+'<createQuickReply v-bind:entityList="this.original"></createQuickReply>'
						+'<createGenericButtons v-bind:entityList="this.original"></createGenericButtons>'
						+'<createAttachment v-bind:entityList="this.original"></createAttachment>'
						+'<div class="search"><label>{{$t("message.search")}}&nbsp;&nbsp;</label><input type="text" v-model="searchText" v-on:keyup="search"/></div>'
					+'</div>'
					+'<div class="col-md-2 fancyLeftColumn">'
						+'<ul v-for="intent in this.original"><li v-for="i in intent"><span style="cursor:pointer;" v-on:click="showOnlyThisItem(i)">{{i.value}}</span></li></ul>'
					+'</div>'
					+'<div class="col-md-10">'
						+'<answersRow v-for="intentArray in this.intentList" v-bind:array="intentArray"></answersRow>'
					+'</div>'
				+'</div> <!--content-->'
			+'</div> <!--container-->',
	methods : {
		showOnlyThisItem : function(intent){
			this.intentList = [[intent]];
		},
		search : function(){
			if(this.searchText.trim() == ""){
				this.immutableObjectToEntity();
				return;
			}
			this.immutableObjectToEntity();
			for(var i = 0; i < this.original.length; i++){
				for(var j = 0; j < this.original[i].length; j++){
					if(this.original[i][j].value.toLocaleUpperCase().indexOf(this.searchText.toLocaleUpperCase()) < 0){
						var k = 0 ;
						for(k = 0; k < this.intentList.length; k++){
							var z = 0 ;
							var flag = false;
							for(var z = 0; z < this.intentList[k].length; z++){
								if(this.intentList[k][z].value == this.original[i][j].value){
									flag = true;
									break;
								}
							}
							if(flag){
								this.intentList[k].splice(z,1);
							}
						}
					}
				}
			}
		},
		immutableObjectToOriginal: function(){
			for(var i = 0; i < this.intentList.length; i++){
				var mod3Array = [];
				for(var j = 0; j < this.intentList[i].length; j++){
					var obj = {};
					for(var key in this.intentList[i][j]){
						obj[key] = this.intentList[i][j][key];
					}
					mod3Array = mod3Array.concat(obj);
				}
				this.original.push(mod3Array);
			}
		},
		immutableObjectToEntity: function(){
			this.intentList = [];
			for(var i = 0; i < this.original.length; i++){
				var mod3Array = [];
				for(var j = 0; j < this.original[i].length; j++){
					var obj = {};
					for(var key in this.original[i][j]){
						obj[key] = this.original[i][j][key];
					}
					mod3Array = mod3Array.concat(obj);
				}
				this.intentList.push(mod3Array);
			}
		},
		mountFunc : function(iList,func){
			Vue.http.get(contextPath + "/secure/api/get/witai/entities").then(function(resp){
				  var counter = 0;
					var index = -1;
					for(var i = 0; i < resp.data.values.length; i++){
						if(counter % 3 == 0){
							index++;
							iList[index] = [];
						}
						iList[index].push(resp.data.values[i]);
						counter++;
					}
					func();
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.mountFunc(this.intentList, this.immutableObjectToOriginal);
	  })
	},
	data :	function () {
		return {intentList : [], original : [], searchText : ""}
	}
});


//Subject sayfasi
var subjectContainer = Vue.component("subjectContainer", {
	template : '<div class="container">'
					+'<div class="header">'
						+'<div class="page-header">'
								+'<div class="headersp">'
									+'<h1>{{$t("message.subjectPage")}}</h1>'
								+'</div>'
								+'<routerLinkComponent></routerLinkComponent>'
								+'<span class="navbar-nav rootRight"">'
									+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
								+'</span>'
								+'<span class="navbar-nav rootRight">'
									+'<i18n_custom></i18n_custom>'
								+'</span>'
						+'</div> <!--page-header-->'
					+'</div> <!--header-->'
					+'<div class="content">'
						+'<br/>'
						+'<form class="form-horizontal">'
							+'<div class="form-group" style="display:inline-block;width:100%;">'
								+'<div class="col-sm-1" style="padding-left:3%"><label>{{$t("message.subject")}}</label></div>'
								+'<div class="col-sm-5">'
									+'<input type="text" class="form-control" v-model="subject" id="subject"/>'
								+'</div>'
								+'<div class="col-md-2">'
									+ '<button type="button" class="btn btn-info" v-on:click="save">{{$t("message.save")}}</button>'
								+'</div>'
							+'</div>'
							+'<br/><br/>'
							+'<div class="table-responsive fancy">'
								+'<table class="table" style="margin-top:2%;">'
									+'<tbody>'
										+'<tr v-for="subject in subjectList.value">'
											+'<td><label>{{$t("message.subject")}} :</label>&nbsp;&nbsp;&nbsp;&nbsp;{{subject.subject}}</td>'
											+'<td><a class="btn btn-default" role="button" v-on:click="deleteFunc(subject.subject)">{{$t("message.remove")}}</a></td>'
											+'<td>'							
												+'<label>{{$t("message.responseList")}}:</label>&nbsp;&nbsp;<input type="text" v-model="subject.response"/>'
											+'</td>'
											+'<td>'
												+'<button type="button" class="btn btn-info" v-on:click="addDefaultResponse(subject)">{{$t("message.save")}}</button>'
											+'</td>'
											+'<td>'
												+'<select v-model="subject.selectedResponse"><option v-for="resp in subject.responseList" v-bind:value="resp">{{resp}}</option></select>'
											+'</td>'
											+'<td>'
												+'<button type="button" class="btn btn-default" v-on:click="deleteDefaultMessage(subject)">{{$t("message.remove")}}</button>'
											+'</td>'
										+'</tr>'
									+'</tbody>'
								+'</table>'
							+'</div>'
							+'<div class="form-group">'
							+'</div>'
						+'</form>'
					+'</div> <!--content-->'
				+'</div> <!--container-->',
		data : function(){
			return {subject : "", subjectList : {value : {}}, response : "", selectedResponse : "", responseList : []};
		},
		methods : {
			save : function(){
				Vue.http.post(contextPath + '/secure/api/mongo/post/subject', {subject: this.subject}, function(resp){
					window.location.reload();
				});	
			},
			deleteFunc : function(sbjct){
				Vue.http.delete(contextPath + '/secure/api/mongo/delete/subject', {subject: sbjct}, function(resp){
					window.location.reload();
				});	
			},
			addDefaultResponse : function(subject){
				Vue.http.post(contextPath + '/secure/api/mongo/post/subject', {fallback: subject}, function(resp){
					window.location.reload();
				});	
			},
			deleteDefaultMessage : function(subject){
				Vue.http.delete(contextPath + '/secure/api/mongo/delete/subject', {fallback: subject}, function(resp){
					window.location.reload();
				});	
			}
		},
		mounted : function(){
			var subjectList = this.subjectList;
			var responseList = this.responseList;
			Vue.http.get(contextPath + '/secure/api/mongo/get/subjects', function(resp){
				subjectList.value = resp;
				for(var j = 0; j < subjectList.value.length; j++){
					if(subjectList.value[j].response){
						subjectList.value[j].responseList = [];
						for(var i = 0; i < subjectList.value[j].response.length; i++){
							subjectList.value[j].responseList.push(subjectList.value[j].response[i]);
						}
					}
					subjectList.value[j].response = "";
				}
			});
		}
});


// Training sayfasi
var trainingContainer = Vue.component("trainingContainer", {
	template:'<div class="container">'
						+'<div class="header">'
							+'<div class="page-header">'
								+'<div class="headersp">'
									+'<h1>{{$t("message.trainingPage")}}</h1>'
								+'</div> <!--headersp-->'
								+'<routerLinkComponent></routerLinkComponent>'
								+'<span class="navbar-nav rootRight">'
									+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
								+'</span>'
								+'<span class="navbar-nav rootRight">'
									+'<i18n_custom></i18n_custom>'
								+'</span>'
							+'</div> <!--page-header-->'
						+'</div> <!--header-->'
						+'<div class="content">'
							+'<div class="col-md-12">'
								+'<training v-for="validateText in this.validateTextList" v-bind:array="validateText"></training>'
							+'</div> <!--col-md-12-->'
						+'</div> <!--content-->'
					+'</div> <!--container-->',
	methods : {
		mountFunc : function(iList){
				Vue.http.get(contextPath + "/secure/api/mongo/findByLimitTen/training_messages").then(function(resp){
						for(var i = 0; i < resp.data.length; i ++){
							iList.push(resp.data[i]);
						}
				});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.mountFunc(this.validateTextList);
	  })
	},
	data :	function () {
		return {validateTextList :[]}
	}
});


//Training template
Vue.component('training', {
	template :'<div class="row">'
					+'<div class="col-md-12">'
							+'<div class="thumbnail niceMng">'
						  	+'<div class="caption">'
									+'<h3 v-html="array.message.text"></h3>'
									+'<p>'
										+'<select v-model="selectedIntent" >'
											+'<option v-for="intent in intentList" v-bind:value="intent.value">{{intent.value}}</option>'
										+'</select>'
										+'<span style="float:right;">{{array.confidenceLevel}}</span>'
										+'<a class="btn btn-info" style="margin:3%;" role="button" v-on:click="validate">{{$t("message.addValidation")}}</a>'
										+'<a class="btn btn-default" role="button" v-on:click="deleteMessage">{{$t("message.removeValidation")}}</a>'
									+'</p>'
							  +'</div> <!--caption-->'
							+'</div> <!--thumbnail-->'
					  +'</div> <!--col-md-12-->'
				+'</div> <!--row-->',
	props: ['array'],
	methods : {
		validate : function(){
			Vue.http.post(contextPath + "/secure/api/witai/validate", {"intent" : this.selectedIntent, "message" : this.array.message.text}).then(function(resp){
				window.location.reload();
			});
		},
		deleteMessage : function(){
			Vue.http.post(contextPath + "/secure/api/witai/delete", {"message" : this.array.message.text}).then(function(resp){
				window.location.reload();
			});
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			this.selectedIntent = this.array.intentName;
			if(this.array.confidenceLevel)
			this.array.confidenceLevel = Math.round(this.array.confidenceLevel * 1000) / 1000;
				var intentListTemp = this.intentList;
				Vue.http.get(contextPath + "/secure/api/get/witai/entities").then(function(resp){
						for(var i = 0; i < resp.data.values.length; i++){
							intentListTemp.push(resp.data.values[i]);
						}
				});
	  });
	},
	data :	function () {
		return {sentence : {value : ""}, selectedIntent : "", intentList : []}
	}
});


// Emoji user popup
Vue.component('emoji_popup_user', {
	template :'<div id="emojiModalUser" class="modal fade" role="dialog">'
							+'<div class="modal-dialog">'
						    +'<div class="modal-content">'
						      +'<div class="modal-header fancyback">'
						        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
						        +'<h4 class="modal-title">{{$t("message.userEmoji")}}</h4>'
						      +'</div>'
						      +'<div class="modal-body">'
						      +'<div class="text-left" id="emojiPopupSelectUser">'
					            +'<p class="lead emoji-picker-container">'
					            +'<span v-if="errorMessage">{{$t("message.emojiPopupError")}}</span>'
					              +'<input type="text"  class="form-control" name="input" placeholder="" style="border:none" data-emojiable="true">'
					            +'</p>'
								+'</div>'
						      +'</div><!--modal-body-->'
						      +'<div class="modal-footer">'
						      	+'<button type="button" class="btn btn-info" v-on:click="selectItem()">{{$t("message.save")}}</button>'
						      +'</div>'
						    +'</div><!--modal-content-->'
						+'</div><!--modal-dialog-->'
					+'</div><!--myModal-->',
	props : ['emojiList','selectedItem'],
	methods : {
		selectItem : function(){
			var emojiHtml = $("#emojiPopupSelectUser .emoji-wysiwyg-editor").html();
			if(!emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g) || emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g).length > 1){
				this.errorMessage = true;
			}else{
				if(emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g)){
					var matched = emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g);
					var obj = {text : $(matched[0]).attr("alt"), image : matched[0]}
					$("#emojiModalUser").modal('hide');
					this.$emit('clicked', obj);
				}
			}
			$("#emojiPopupSelectUser .emoji-wysiwyg-editor").html('');
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			$('#emojiModalUser').on('hidden.bs.modal', function () {
				$("#emojiPopupSelectUser .emoji-wysiwyg-editor").html('');
			})
		});
	},
	data :	function () {
		return{target : null, selectedText : "", errorMessage : false}
	}
});

// Emoji bot popup
Vue.component('emoji_popup_bot', {
	template :'<div id="emojiModalBOT" class="modal fade" role="dialog">'
							+'<div class="modal-dialog">'
						    +'<div class="modal-content">'
						      +'<div class="modal-header fancyback">'
						        +'<button type="button" class="close whitetxt" data-dismiss="modal">&times;</button>'
						        +'<h4 class="modal-title">{{$t("message.botEmoji")}}</h4>'
						      +'</div>'
						      +'<div class="modal-body">'
						      +'<div class="text-left" id="emojiPopupSelect">'
					            +'<p class="lead emoji-picker-container">'
					            +'<span v-if="errorMessage">{{$t("message.emojiPopupError")}}</span>'
					              +'<input type="text"  class="form-control" name="input" placeholder="" style="border:none" data-emojiable="true">'
					            +'</p>'
								+'</div>'
						      +'</div><!--modal-body-->'
						      +'<div class="modal-footer">'
						      	+'<button type="button" class="btn btn-info" v-on:click="selectItem()">{{$t("message.save")}}</button>'
						      +'</div>'
						    +'</div><!--modal-content-->'
						+'</div><!--modal-dialog-->'
					+'</div><!--myModal-->',
	props : ['emojiList', 'selectedItem'],
	methods : {
		selectItem : function(){	
	
			var emojiHtml = $("#emojiPopupSelect .emoji-wysiwyg-editor").html();
			if(emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g) && emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g).length > 1){
				this.errorMessage = true;
			}else{
				if(emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g)){
					var matched = emojiHtml.match(/(<img\s[^>]*?src\s*=\s*['\"]([^'\"]*?)['\"][^>]*?>)/g);
					var obj = {text : $(matched[0]).attr("alt"), image : emojiHtml}
					$("#emojiModalBOT").modal('hide');
					this.$emit('clicked', obj);
				}else{
					var obj = {text : emojiHtml, image : emojiHtml}
					$("#emojiModalBOT").modal('hide');
					this.$emit('clicked', obj);
				}
				
			}
			$("#emojiPopupSelect .emoji-wysiwyg-editor").html('');
			
		}
	},
	mounted : function(){
		this.$nextTick(function () {
			window.emojiPicker = new EmojiPicker({
	          emojiable_selector : '[data-emojiable=true]',
	          assetsPath : '../../lib/img/',
	          popupButtonClasses : 'fa fa-smile-o'
	        });
			window.emojiPicker.discover();
			
			$('#emojiModalBOT').on('hidden.bs.modal', function () {
				$("#emojiPopupSelect .emoji-wysiwyg-editor").html('');
			})
		});
	},
	data :	function () {
		return{target : null, selectedText : "", errorMessage : false}
	}
});


// Emoji sayfasi
var emojiContainer = Vue.component('emojiContainer', {
	template : '<div class="container">'
		+'<div class="header">'
		+'<div class="page-header">'
					+'<div class="headersp">'
						+'<h1>{{$t("message.emojiPage")}}</h1>'
					+'</div>'
					+'<routerLinkComponent></routerLinkComponent>'
					+'<span class="navbar-nav rootRight">'
						+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
					+'</span>'
					+'<span class="navbar-nav rootRight">'
						+'<i18n_custom></i18n_custom>'
					+'</span>'
				+'</div> <!--page-header-->'
			+'</div> <!--header-->'
			+'<div class="content">'
				+'<table class="table"><tbody><tr>'
				+'<td style="border-top:0;width:30%;">'
					+'<button style="margin:3px;" type="button" class="btn btn-info" v-on:click="showEmojiPopup(\'emojiModalUser\')">{{$t("message.userEmoji")}}</button>'
					+'<button style="margin:3px;" type="button" class="btn btn-info" v-on:click="showEmojiPopup(\'emojiModalBOT\')">{{$t("message.botEmoji")}}</button>'
				+'</td>'
				+'<td style="border-top:0;width:30%;">'
					+'<span v-html="selectedEmojiUser.image"></span> -- <span v-html="selectedEmojiBOT.image"></span>'
				+'</td>'
				+'<td style="border-top:0;">'
					+'<button type="button" class="btn btn-info" v-on:click="save()">{{$t("message.save")}}</button>'
				+'</td>'
				+'</tr></tbody></table>'
				+'<br/><br/>'
				+'<emoji_popup_user v-bind:emojiList="emojiList" v-bind:selectedItem="selectedEmojiUser" v-on:clicked=setUserEmoji></emoji_popup_user>'
				+'<emoji_popup_bot v-bind:emojiList="emojiList" v-bind:selectedItem="selectedEmojiBOT" v-on:clicked=setBotEmoji></emoji_popup_bot>'
				+ '<div class="table-responsive fancy">'
					+'<table class="table">'
						+'<thead class="borderBtm">'				
							+'<tr><th style="border-bottom:0;">{{$t("message.userEmoji")}}</th><th style="border-bottom:0;">{{$t("message.botReply")}}</th></tr>'
						+'</thead>'
						+'<tbody>'
							+'<tr v-for="relation in emojiRelation.value">'
								+'<td v-html="relation.source.image"></td><td v-html="relation.target.image"></td>'
								+'<td><button type="button" class="btn btn-default" v-on:click="deleteEmoji(relation.source)">{{$t("message.remove")}}</button></td>'
							+'</tr>'
						+'</tbody>'
					+'</table>'
				+'</div>'
			+'</div> <!--content-->'
		+'</div> <!--container-->',
		data :	function () {
			return {emojiList : {value : {}}, selectedEmojiUser : {}, selectedEmojiBOT : {}, emojiRelation : {value : {}}}
		},
		methods :{
			showEmojiPopup : function(modal){
				$("#" + modal).modal();
			},
			setUserEmoji : function(item){
				this.selectedEmojiUser = item;
			},
			setBotEmoji : function(item){
				this.selectedEmojiBOT = item;
			}
			,
			save : function(){
				if(this.selectedEmojiUser.image && this.selectedEmojiBOT.image)
				Vue.http.post(contextPath + '/secure/api/save/emoji/relation', {source : this.selectedEmojiUser, target : this.selectedEmojiBOT}, function(resp){
					window.location.reload();
				});				
			},
			deleteEmoji : function(source){
				if(source){
					Vue.http.delete(contextPath + '/secure/api/delete/emoji/relation', {text : source.text}, function(resp){
						window.location.reload();
					});	
				}
			}
		},
		mounted : function(){
			var emoji = this.emojiList;
			Vue.http.get(contextPath + '/secure/api/mongo/emoji/get', function(resp){
				emoji.value = resp;
			});
			var emojiRelation = this.emojiRelation;
			Vue.http.get(contextPath + '/secure/api/mongo/emojiRelation/get', function(resp){
				emojiRelation.value = resp;
			});
		}
});



//configs sayfasi
var configsContainer = Vue.component("configsContainer", {
	template:'<div class="container">'
					+'<div class="header">'
						+'<div class="page-header">'
							+'<div class="headersp">'
								+'<h1>{{$t("message.configsPage")}}</h1>'
							+'</div>'
							+'<routerLinkComponent></routerLinkComponent>'
							+'<span class="navbar-nav rootRight">'
								+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
							+'</span>'
							+'<span class="navbar-nav rootRight">'
								+'<i18n_custom></i18n_custom>'
							+'</span>'
						+'</div> <!--page-header-->'
					+'</div> <!--header-->'
					+'<br/>'
					+'<div class="content">'
						+'<div class="row col-md-12 fancy" style="margin-left:1%;">'
							+'<br/>'
							+'<div class="col-sm-4"><label>{{$t("message.threshold")}} :&nbsp;&nbsp;</label>'
								+'<select v-on:change="changeThreshold" v-model="threshold.val">'
									+'<option value="0.1">0.1</option><option value="0.2">0.2</option>'
									+'<option value="0.3">0.3</option><option value="0.4">0.4</option>'
									+'<option value="0.5">0.5</option><option value="0.6">0.6</option>'
									+'<option value="0.7">0.7</option><option value="0.8">0.8</option>'
									+'<option value="0.9">0.9</option>'
								+'</select>'
							+'</div>'
							+'<br/><br/><br/>'
							+'<div class="col-md-12">'
								+'<label>{{$t("message.generalResponseList")}} :</label>&nbsp;&nbsp;<input type="text" v-model="response"/>'
								+'&nbsp;&nbsp;<button type="button" class="btn btn-info" v-on:click="addDefaultResponse">{{$t("message.save")}}</button>'
								+'&nbsp;&nbsp;<select v-model="selectedResponse"><option v-for="resp in responseList" v-bind:value="resp">{{resp}}</option></select>'
								+'&nbsp;&nbsp;<button type="button" class="btn btn-default" v-on:click="deleteDefaultMessage">{{$t("message.remove")}}</button>'
							+'</div> <!--col-md-12-->'
							+'<br/><br/><br/>'
						+'</div> <!--row col-md-12-->'
						
						+'<div class="row col-md-12" style="margin-left:1%;margin-top:1% !important;">'
							+'<h3>{{$t("message.persistentMenu")}}</h3>'
						+'</div>'
						+'<br/>'
						+'<div class="col-md-12 fancy" style="margin-left:1%;">'
								+'<div class="form-group">'
									+'<br/>'
									+'<table class="table">'
										+'<tbody>'
											+'<tr>'
												+'<td style="border-top:0 !important;">'
													+'<label>{{$t("message.menu")}}</label>'
												+'</td>'
												+'<td style="border-top:0 !important;">'
													+'<label>{{$t("message.text")}} / {{$t("message.link")}}</label>'
												+'</td>'
											+'</tr>'
											+'<tr>'
												+'<td style="border-top:0 !important;">'
													+'<input v-model="pMenuItemName" type="text"/>'
												+'</td>'
												+'<td style="border-top:0 !important;">'
													+'<input v-model="pMenuItem" type="text"/>'
												+'</td>'
												+'<td style="border-top:0 !important;">'
													+'<button type="button" class="btn btn-info" v-on:click="addPersistentMenu()">{{$t("message.add")}}</button>'
												+'</td>'
											+'</tr>'										
											+'<tr v-for="item in persistentMenuList">'
												+'<td>{{item.name}}</td>'
												+'<td>{{item.text}}</td>'
												+'<td>'
													+'<button type="button" class="btn btn-default" v-on:click="removePersistentMenu(item)">{{$t("message.remove")}}</button></li>'
												+'</td>'
											+'</tr>'
										+'</tbody>'
									+'</table>'
								+'</div> <!--form-group-->'
							+'</form> <!--form-horizontal-->'
						+'</div> <!--col-md-12 fancy-->'
					+'</div> <!--content-->'
				+'</div> <!--container-->',
	methods : {
			changeThreshold : function(){
				Vue.http.get(contextPath + "/secure/api/change/threshold?threshold=" + this.threshold.val).then(function(resp){
				});
			},
			addDefaultResponse : function(){
				this.responseList.push(this.response);
				Vue.http.get(contextPath + "/secure/api/add/responseList?response=" + this.response).then(function(resp){
					
				});
				this.response = "";
			},
			deleteDefaultMessage : function(){
				if(this.selectedResponse.trim() != ""){
					this.responseList.splice(this.responseList.indexOf(this.selectedResponse),1);
					Vue.http.delete(contextPath + "/secure/api/delete/responseList?response=" + this.selectedResponse).then(function(resp){
					});
				}
			},
			addPersistentMenu : function(){
				this.persistentMenuList.push({text : this.pMenuItem, name : this.pMenuItemName});
				this.pMenuItem = "";
				this.pMenuItemName = "";
				Vue.http.post(contextPath + '/secure/api/add/persistentMenu', {persistentMenuList : this.persistentMenuList}, function(resp){
				});
			},
			
			removePersistentMenu : function(item){
				this.persistentMenuList.splice(this.persistentMenuList.indexOf(item),1);
				Vue.http.post(contextPath + '/secure/api/add/persistentMenu', {persistentMenuList : this.persistentMenuList}, function(resp){
				});
			}
	},
	mounted : function(){
		this.$nextTick(function () {
			var thresholdTemp = this.threshold;
			var responseListTemp = this.responseList;
			var pMenu = this.persistentMenuList;
			Vue.http.get(contextPath + "/secure/api/get/threshold/").then(function(resp){
					thresholdTemp.val =resp.data[0].threshold;
					for(var i = 0; i < resp.data[0].responseList.length; i++){
						responseListTemp.push(resp.data[0].responseList[i]);
					}
					for(var i = 0; i < resp.data[0].persistentMenu.length; i++){
						pMenu.push({text : resp.data[0].persistentMenu[i].text, name : resp.data[0].persistentMenu[i].name});
					}
			});
	  })
	},
	data :	function () {
		return {threshold : {val : 0.7}, responseList : [], response : "", selectedResponse : "", pMenuItem : "", pMenuItemName : "", persistentMenuList : []}
	}
});



var webChatContainer = Vue.component("webChatContainer", {
	template : '<div class="container">'
		+'<div class="header">'
			+'<div class="page-header">'
				+'<div class="headersp">'
						+'<h1>{{$t("message.webChatPage")}}</h1>'
					+'</div>'
					+'<routerLinkComponent></routerLinkComponent>'
					+'<span class="navbar-nav rootRight">'
						+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
					+'</span>'
					+'<span class="navbar-nav rootRight">'
						+'<i18n_custom></i18n_custom>'
					+'</span>'
				+'</div> <!--page-header-->'
			+'</div> <!--header-->'
			+'<div class="content">'
				+'<div><b>Please copy this to your web site.</b></div>'
				+'<div style="width:500px">{{iframeContent}}</div>'
				+'<div style="position:absolute !important;bottom:0px;right:15px;">'
					+'<table>'
						+'<tr><td><button type="button" style="width:370px" class="big-btn btn btn-info" data-toggle="collapse" data-target="#container">{{$t("message.talkToBot")}}</button></td></tr>'
						+'<tr><td><iframe class="collapse in" id="container" style="border:none;border-left:1px solid #c3c3c3 !important" src="http://localhost:8000/webchat.html?accessToken='+localStorage.getItem('globalAccessToken')+'&authorization='+localStorage.getItem('id_token')+'" width="370px" height="420px" /></td></tr>'
					+'</table>'
				+'</div>'
			+'</div> <!--content-->'
		+'</div> <!--container-->'	,
		data : function(){
			return {
				iframeContent : '<div style="position:absolute !important;bottom:0px;right:15px;">'
										+'<table>'
										+'<tr><td><button type="button" style="width:370px" class="big-btn btn btn-info" data-toggle="collapse" data-target="#container">BOT</button></td></tr>'
										+'<tr><td><iframe class="collapse in" id="container" style="border:none;border-left:1px solid #c3c3c3 !important" src="http://www.chatbotpanel.com:8000/webchat.html?accessToken='+localStorage.getItem('globalAccessToken')+'&authorization='+localStorage.getItem('id_token')+'" width="370px" height="420px" /></td></tr>'
									+'</table>'
								+'</div>'
				
				
			}
		}
});


// Facebook sayfasi
var facebookContainer = Vue.component("facebookContainer", {
	template:'<div class="container">'
						+'<div class="header">'
							+'<div class="page-header">'
								+'<div class="headersp">'
									+'<h1>{{$t("message.facebookPage")}}</h1>'
								+'</div>'
								+'<routerLinkComponent></routerLinkComponent>'
								+'<span class="navbar-nav rootRight">'
									+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
								+'</span>'
								+'<span class="navbar-nav rootRight">'
									+'<i18n_custom></i18n_custom>'
								+'</span>'
							+'</div> <!--page-header-->'
						+'</div> <!--header-->'
						+'<div class="content">'
							+'<br/>'
							+'<div class="col-md-10 fancy">'
							+'<br/>'
							+'<form class="form-horizontal">'
								+'<div class="form-group">'
									+'<label class="control-label col-sm-2" for="pageId">Page Id:</label>'
									+'<div class="col-sm-10">'
										+'<input type="text" class="form-control" v-model="facebookDeployment.values.pageId" id="pageId" placeholder="Page Id">'
									+'</div>'
								+'</div>'
								+'<div class="form-group">'
									+'<label class="control-label col-sm-2" for="appSecret">App Secret:</label>'
									+'<div class="col-sm-10">'
										+'<input type="text" class="form-control" id="appSecret" v-model="facebookDeployment.values.appSecret" placeholder="App Secret">'
									+'</div>'
								+'</div>'
								+'<div class="form-group">'
									+'<label class="control-label col-sm-2" for="accessToken">Access Token:</label>'
									+'<div class="col-sm-10">'
										+'<input type="text" class="form-control" id="accessToken" placeholder="Access Token" v-model="facebookDeployment.values.accessToken">'
									+'</div>'
								+'</div>'
								+'<div class="form-group">'
									+'<label class="control-label col-sm-2" for="verifyToken">Verify Token:</label>'
									+'<div class="col-sm-10">'
										+'<input type="text" class="form-control" id="verifyToken" placeholder="Verify Token" v-model="facebookDeployment.values.verifyToken">'
									+'</div>'
								+'</div>'
								+'<div class="form-group">'
									+'<label class="control-label col-sm-2" for="appId">App Id:</label>'
									+'<div class="col-sm-10">'
										+'<input type="text" class="form-control" id="appId" placeholder="App Id" v-model="facebookDeployment.values.appId">'
									+'</div>'
								+'</div>'
								+'<div class="form-group">'
									+'<div class="col-sm-offset-2 col-sm-10">'
									+'<span style="color:green;margin-right:20px" v-if="isDeployed.value">{{$t("message.success")}}</span>'	
									+'<button type="button" class="btn btn-info" v-on:click="deploy">{{$t("message.deployBtn")}}</button>'
									+'</div>'
								+'</div>'
							+'</form>'
							+'</div>'
						+'</div> <!--content-->'
					+'</div> <!--container-->',
	methods : {
			deploy : function(){
				var tempIsDeployed = this.isDeployed;
				Vue.http.post(contextPath + '/secure/api/facebook/post', {facebookDeployment : this.facebookDeployment.values}, function(resp){
					tempIsDeployed.value = true;
				});
			}
	},
	mounted : function(){
		this.$nextTick(function () {
			var facebookTemp = this.facebookDeployment;
				Vue.http.get(contextPath + '/secure/api/facebook/get', function(resp){
					facebookTemp.values = resp[0].facebookDeployment;
				});
	  })
	},
	data :	function () {
		return {facebookDeployment : {values : {}}, isDeployed : {value : false}}
	}
});


// witai sayfasi
var witDeployContainer = Vue.component("witDeployContainer", {
	template : '<div class="container">'
		+'<div class="header">'
			+'<div class="page-header">'
				+'<div class="headersp">'
						+'<h1>{{$t("message.witaiPage")}}</h1>'
					+'</div>'
					+'<routerLinkComponent></routerLinkComponent>'
					+'<span class="navbar-nav rootRight">'
						+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
					+'</span>'
					+'<span class="navbar-nav rootRight">'
						+'<i18n_custom></i18n_custom>'
					+'</span>'
				+'</div> <!--page-header-->'
			+'</div> <!--header-->'
			+'<div class="content">'
				+'<br/>'
				+'<div class="col-md-10 fancy">'
				+'<br/>'
				+'<form class="form-horizontal">'
					+'<div class="form-group">'
						+'<label class="control-label col-sm-2" for="authToken">{{$t("message.authToken")}}</label>'
						+'<div class="col-sm-10">'
							+'<input type="text" class="form-control" v-model="witDeployment.value" id="authToken" placeholder="Authorization Token">'
						+'</div>'
					+'</div>'
					+'<div class="form-group">'
						+'<div class="col-sm-offset-2 col-sm-10">'
							+'<span style="color:green;margin-right:20px" v-if="isDeployed.value">{{$t("message.success")}}<span style="padding-left:5px;color:red;margin-right:20px">{{$t("message.savewitaikey")}}</span></span>'	
							+'<button type="button" class="btn btn-info" v-on:click="deploy">{{$t("message.deployBtn")}}</button>'
						+'</div>'
					+'</div>'
				+'</form>'
				+'<br/><hr/><br/>'
				+'<form class="form-horizontal">'
					+'<div class="form-group">'
						+'<label class="control-label col-sm-2">{{$t("message.appLang")}}</label>'
						+'<div class="col-sm-10">'
							+'<select v-model="app.language"><option value="tr">TR</option><option value="en">EN</option><option value="fr">FR</option></select>'
						+'</div>'
					+'</div>'
					+'<div class="form-group">'
						+'<label class="control-label col-sm-2">{{$t("message.appName")}}</label>'
						+'<div class="col-sm-10">'
							+'<input type="text" class="form-control" v-model="app.name" placeholder="">'
						+'</div>'
					+'</div>'
					+'<div class="form-group">'
						+'<label class="control-label col-sm-2">{{$t("message.appDescription")}}</label>'
						+'<div class="col-sm-10">'
							+'<input type="text" class="form-control" v-model="app.description" placeholder="">'
						+'</div>'
					+'</div>'
					+'<div class="form-group">'
						+'<div class="col-sm-offset-2 col-sm-10">'
							+'<button type="button" class="btn btn-info" v-on:click="create">{{$t("message.create")}}</button>'
						+'</div>'
					+'</div>'
				+'</form>'
			+'</div>'
			+'</div> <!--content-->'
		+'</div> <!--container-->',
		data :	function () {
			return {witDeployment : {value : ""}, isDeployed : {value : false}, app : {}}
		},
		methods :{
			deploy:function(){
				var tempIdDeployed = this.isDeployed;
				localStorage.setItem('globalAccessToken',this.witDeployment.value);
				Vue.http.post(contextPath + '/secure/api/witaiDeploy/post', {witDeployment : this.witDeployment.value}, function(resp){
					tempIdDeployed.value = true;
				});
			},
			create : function(){
				this.app.prvt = "true";
				Vue.http.post(contextPath + '/secure/api/witaiCreateApp/post', {application : this.app}, function(resp){
					if(resp.errors){
						var lang = window.localStorage.getItem('lang');						
						if(lang == "tr"){
							alert("Bu isim daha önce alınmıştır, lütfen başka bir isim giriniz.");
						}else if (lang == "fr"){
							alert("Ce nom a déjà été pris, veuillez entrer un autre nom.");
						}else{
							alert("This name was previously taken, please enter another name.");
						}						
					}else{
						localStorage.setItem('globalAccessToken',""+resp.access_token);
						Vue.http.post(contextPath + '/secure/api/witaiDeploy/post', {witDeployment : resp.access_token}, function(resp){
							window.location.reload();
						});
					}
					
				});
			}
		},
		mounted : function(){
			this.$nextTick(function () {
				var witTemp = this.witDeployment;
					Vue.http.get(contextPath + '/secure/api/witaiDeploy/get', function(resp){
						witTemp.value = resp.defaultAuthorizationToken;
					});
		  });
		}
	
});

var chatbaseContainer = Vue.component('chatbaseContainer', {
	template : '<div class="container">'
		+'<div class="header">'
			+'<div class="page-header">'
				+'<div class="headersp">'
						+'<h1>{{$t("message.chatbasePage")}}</h1>'
					+'</div>'
					+'<routerLinkComponent></routerLinkComponent>'
					+'<span class="navbar-nav rootRight">'
					+	'<span style="margin-right:5px">{{userName}}</span>'
						+'<a target="_blank" href="./root/adminUser.xhtml"><b>{{$t("message.rootPanel")}}</b></a>'
					+'</span>'
					+'<span class="navbar-nav rootRight">'
						+'<i18n_custom></i18n_custom>'
					+'</span>'
				+'</div> <!--page-header-->'
			+'</div> <!--header-->'
			+'<div class="content">'
				+'<br/>'
				+'<div class="col-md-10 fancy">'
				+'<br/>'
					+'<form class="form-horizontal">'
						+'<div class="form-group">'
							+'<label class="control-label col-sm-2" for="authToken">{{$t("message.authToken")}}</label>'
							+'<div class="col-sm-10">'
								+'<input type="text" class="form-control" v-model="chatbaseDeployment.value" id="authToken" placeholder="Authorization Token">'
							+'</div>'
						+'</div>'
						+'<div class="form-group">'
							+'<div class="col-sm-offset-2 col-sm-10">'
								+'<span style="color:green;margin-right:20px" v-if="isDeployed.value">{{$t("message.success")}}</span>'	
								+'<button type="button" class="btn btn-info" v-on:click="deploy">{{$t("message.deployBtn")}}</button>'
							+'</div>'
						+'</div>'
					+'</form>'					
				+'</div>'
			+'</div> <!--content-->'
		+'</div> <!--container-->',
		data :	function () {
			return {chatbaseDeployment : {value : ""}, isDeployed : {value : false}, app : {} , userName :  localStorage.getItem('userName')}
		},
		methods :{
			deploy:function(){
				var tempIdDeployed = this.isDeployed;
				Vue.http.post(contextPath + '/secure/api/chatbase/post', {chatbaseDeployment : this.chatbaseDeployment.value}, function(resp){
					tempIdDeployed.value = true;
				});
			}
		},
		mounted : function(){
			this.$nextTick(function () {
				var chatbaseTemp = this.chatbaseDeployment;
					Vue.http.get(contextPath + '/secure/api/chatbase/get', function(resp){
						chatbaseTemp.value = resp[0].chatbaseAppSecret;
					});
		  });
		}
});


// Menu isimleri
var vrouter = new VueRouter({
	routes: [
		{name: 'home', path: '/', component: container},
		{name: 'answersContainer', path: '/answers', component: answersContainer},
		{name: 'subjectContainer', path: '/subjects', component: subjectContainer},
		{name: 'trainingContainer', path: '/trainings', component: trainingContainer},
		{name: 'emojiContainer', path: '/emojis', component: emojiContainer},
		{name: 'configsContainer', path: '/configs', component: configsContainer},
		{name: 'webChatContainer', path: '/webchat', component: webChatContainer},
		{name: 'facebookContainer', path: '/facebook', component: facebookContainer},
		{name: 'witContainer', path: '/witai', component: witDeployContainer},
		{name: 'chatbaseContainer', path: '/chatbase', component: chatbaseContainer}
	]
});


var vue = new Vue({
  el: '#app',
  data: { userName : ""},
  methods : {},
	mounted: function () {
	  this.$nextTick(function () {
		  
	  })
	},
  methods : {},
  router : vrouter,
  i18n : i18n
});

