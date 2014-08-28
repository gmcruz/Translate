
	var domainName = "http://127.0.0.1";
	
	
	$(document).ready(function(){				
		
		//Set the languages from the start. This will change as more translations are put into the DB.
		var fromLang = 149;
		var toLang = 140;	
		
		
		
		
		//TODO ONLY TESTING SHOULD GO HERE TEMPORARILY
		$("#testArea").hide();			
		$("#testTextButton").click(function () {
			$("#textToProcess").val( $("#testText").val() );
		});			
		
		
		
		
		
		
		$("#transformTextareaShowP").hide();
		
		$(".showMeaning").tooltip({
			show: {effect: "slideDown", delay: 250}
		});	

		$("#transformTextareaShowButton").click(function () {
			$("#contentElem").html("");		
			$("#newTransformationTextarea").show();
			$("#transformTextareaShowP").hide();
		});			
		
		$("#transformSubmit").click(function () {				    
		    var toFromLanguages = setTransformation($("#textToProcess").val(), $("#fromlanguageOptions option:selected").val(), $("#tolanguageOptions option:selected").val());
		    $("#newTransformationTextarea").hide();
		    $("#transformTextareaShowP").show();
		});	
		
		$("#fromlanguageOptions").change(function () {				    
		    var toFromLanguages = setTransformation($("#textToProcess").val(), $("#fromlanguageOptions option:selected").val(), $("#tolanguageOptions option:selected").val());	
		});		

		$("#tolanguageOptions").change(function () {				    
		    var toFromLanguages = setTransformation($("#textToProcess").val(), $("#fromlanguageOptions option:selected").val(), $("#tolanguageOptions option:selected").val());	
		});
				
		$("#addTranslationSubmit").click(function(){				
			var id = $("#fromLangTranslationId").val();
			var fromLang = $("#newTranslationFromLang").val();
			var toLang = $("#newTranslationToLang").val();
			var word = $("#newTranslation").val();
			var fromWord = $("#fromTranslationWord").val();

	        $.ajax({
	            type: "POST",
	            url: domainName + "/Lingoli/resource/words/post",
	            dataType: "json",
	            contentType: "application/json; charset=UTF-8",
	            data: { fromlocaleid: fromLang, fromWord: fromWord, word: word, localeid: toLang, maptoid: id } ,
	            success: function (msg) { }			            
	        });
	        closeNewTranslationPopup();
	        
	        var toFromLanguages = setTransformation($("#textToProcess").val(), $("#fromlanguageOptions option:selected").val(), $("#tolanguageOptions option:selected").val());	
			
		});	 
							
		//Change the language select boxes languages.
		changeSelectLanguages(fromLang, toLang);
		
		
	}); //Finishes $(document).ready(function(){
		
	
	
	function changeSelectLanguages(fromLang, toLang){//TODO does this need to happen multiple times?
		var fromOptions = $("#fromlanguageOptions");
		var toOptions = $("#tolanguageOptions");
		$.getJSON( domainName+"/Lingoli/resource/locales/active", function( data ) {													
			if (data.hasOwnProperty("locale")) {						
				$.each( data.locale, function( key, valLocale ) {							
					
					var existsInFrom = false;
					$("#fromlanguageOptions option").each(function(from){
						if($(this).val() == valLocale.id){
							existsInFrom = true;
							$(this).selected = true;
							return false;
						}
					});					
					if(!existsInFrom){
						var fromOption = new Option(this.language, this.id);
						if(valLocale.id == fromLang){
							fromOption.selected = true;	
						}
						fromOptions.append(fromOption);		
					}

					var existsInTo = false;
					$("#tolanguageOptions option").each(function(to){
						if($(this).val() == valLocale.id){
							existsInTo = true;
							$(this).selected = true;	
							return false;
						}
					});					
					if(!existsInTo){
						var toOption = new Option(this.language, this.id);
						if(valLocale.id == toLang){
							toOption.selected = true;	
						}
						toOptions.append(toOption);		
					}								
					
				})
			}	
		})	
	}
		
	function getObjects(obj, key, val) {			
	    var objects = [];
	    for (var i in obj) {		    	
	    	if (!obj.hasOwnProperty(i)) continue;
	        if (typeof obj[i] == 'object') {
	            objects = objects.concat(getObjects(obj[i], key, val));
	        } else if (i == key && obj[key] == val && obj.word != undefined) {		        	
	            objects.push(obj);
	        }
	    }
	    return objects;
	}
				
	function closeNewTranslationPopup() {				 			       
		clearNewTranslationPopupForm();					
        deselect();			
        $("#newTranslationPopup").slideFadeToggle('0'); 
	};			
	
	function clearNewTranslationPopupForm() {				 			       
		$("#new_userTranslationform").find('input:text, input:hidden, input:password, input:file, select, textarea').val('');
		$("#new_userTranslationform").find('input:radio, input:checkbox').removeAttr('checked').removeAttr('selected');					       
	};
	
	function deselect() {
		$(".newTranslationPopup").slideFadeToggle(function() { 
			$("#newTranslationDiv").removeClass("selected");
	    });   
	};
	
	$.fn.slideFadeToggle = function(showHide, easing, callback) {
		return this.animate({ opacity: showHide, height: 'show' }, "fast", easing, callback);
	};
	
	function newTranslationFn(id, fromLang, toLang, word) {
		console.log("newTranslationFn("+id+", "+fromLang+", "+toLang+", "+word+")");
		clearNewTranslationPopupForm();								
		$("#newTranslationPopup").css( {position:"absolute", top:event.pageY, left: event.pageX});				
		$("#fromLangTranslationId").val(id);
        $("#newTranslationFromLang").val(fromLang);
        $("#newTranslationToLang").val(toLang);		
		$("#newTranslationPopup").slideFadeToggle('1'); 
		$("#wordToTranslate").text(word + " =");
		$("#fromTranslationWord").val(word);
		$("#newTranslation").focus();
	};
	
	//Don't show this word anymore for the logged in user.
	function knownWordFn(fromLang, wordid, word) {
		console.log("knownWordFn("+fromLang+", "+wordid+", "+word+")");
        $.ajax({
        	async: false,
        	type: "POST",
            url: domainName + "/Lingoli/resource/users/knownword",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data: { localeid: fromLang, wordid: wordid, word: word } ,
            success: function (msg) {            	
            	//console.log(msg);            	
            }			            
        });
        
        setTransformation($("#textToProcess").val(), $("#fromlanguageOptions option:selected").val(), $("#tolanguageOptions option:selected").val());	
		        
	};
	
	
	//User has asked to re-show this word for whatever reason.
	function unKnownWordFn(fromLang, wordid, word) {
		console.log("unKnownWordFn("+fromLang+", "+wordid+", "+word+")");
        $.ajax({
        	async: false,
        	type: "POST",
            url: domainName + "/Lingoli/resource/users/unknownword",
            dataType: "json",
            contentType: "application/json; charset=UTF-8",
            data: { localeid: fromLang, wordid: wordid, word: word } ,
            success: function (msg) {            	
            	//console.log(msg);            	
            }			            
        });
		
        setTransformation($("#textToProcess").val(), $("#fromlanguageOptions option:selected").val(), $("#tolanguageOptions option:selected").val());	
        
	};
	
	function setTransformation(requestedTextToProcess, requestedFromLang, requestedToLang){
		
		var fromLangSet;
		var toLangSet;
		
	    var unicodePunctuation = XRegExp("^\\p{P}+$");

	  //TODO MAYBE  alert(unicodePunctuation.test("?.,;!¡¿。�·")); // true
		
		 $.ajax({
	          	//aysnc in order to be able to set a variable in the json to be seen outside.
	            async: false,
			 	type: "POST",
	            url: domainName + "/Lingoli/resource/transformations/post",
	            dataType: "json",
	            data: { textToProcess: requestedTextToProcess, fromLang: requestedFromLang, toLang: requestedToLang } ,
	            success: function (data) {
	            				            	
					if (data.hasOwnProperty("language")) {			
						fromLangSet = data.language.from;
						toLangSet = data.language.to;							
					}
					
					if (data.hasOwnProperty("paragraphs")) {				
						var items = [];
						$.each( data.paragraphs, function( key, valParagraphs ) {

							if (valParagraphs.hasOwnProperty("paragraph")) {

								$.each( valParagraphs.paragraph, function( key, valParagraph ) {
									items.push( "<tr>" );									
									$.each( valParagraph.sentences, function( key, valSentence ) {
										
										var meaning = valSentence.meaning;										
																					
										$.each( valSentence.sentence, function( key, valWord ) {
											
											var sentenceLength = valSentence.sentence.length - 1;
																						
											items.push( "<td>" );
											
											//Look ahead to what is next for punctuation
											var next = valSentence.sentence[key+1];
											var prior = valSentence.sentence[key-1];
											var addPunc = "";
											var addPuncFront = ""; 
											
											if(next != undefined && (unicodePunctuation.test(next.word))){													
												if(sentenceLength == (key + 1) && next.word == "."){
													//But if it is a period then we want to put the sentence meaning.
													if(meaning == "NOTAVAILABLE"){
														addPunc = '.';
													}else{
														addPunc = '<span class="showMeaning handCursor" title="' + meaning + '">. </span>';
													}
												}	
												else{																
													if(next.place != undefined && next.place == "front"){
														//addPuncFront = next.word;	
													}else{
														addPunc = next.word;
													}
												}	
											}													
											
											if(prior != undefined && (unicodePunctuation.test(prior.word))){																														
												if(prior.place != undefined && prior.place == "front"){
													addPuncFront = prior.word;	
												}															
											}
											
											//numbers or other words who do not have translations like names or city names etc....
											if((valWord.id == undefined && valWord.translation == undefined) || (valWord.allowChange != undefined && !valWord.allowChange)){
												items.push( '<td><div class="elemWord">' + valWord.word + addPunc + '</div><div class="elemTranslation">&nbsp;</div></td>' );
											}
											//Punctuation just show as is.
											else if(valWord.punc != undefined){
												//don't push out as it has been handled by the peek ahead.												
											}
											//ask for help in getting it translated.
											else if((valWord.empty != undefined && valWord.empty == true) || valWord.translation == "N_A"){
												items.push( '<td><div class="elemWord handCursor noTranslationHelp" onclick="newTranslationFn(' + valWord.id + ',\'' + fromLangSet + '\',\'' + toLangSet + '\',\'' + valWord.word + '\');">' + addPuncFront + valWord.word + addPunc + '</div><div class="invisible">'+valWord.id+'</div></td>' );
											}
											else if(valWord.show != undefined && valWord.show == false){
												items.push( '<td><div class="elemWord">' + addPuncFront +  valWord.word + addPunc + '</div><div class="invisible">' + valWord.translation + '</div></td>' );
											}
											//Ref word must loop to find in returned JSON
											else if(valWord.ref != undefined){		
												var tempRef = getObjects(data, "id", valWord.id);
												var typeClass = "elemTranslation";
												if(tempRef[0].show != undefined && tempRef[0].show == false){
													typeClass = "invisible";
												}													
												items.push( '<td><div class="elemWord">' + addPuncFront + tempRef[0].word + addPunc + '</div><div class="' + typeClass + '">' + tempRef[0].translation + '</div></td>' );
											}
											//Regular word to process   onclick="knownWordFn(' + valWord.id + ',\'' + fromLangSet + '\',\'' + valWord.word + '\');"
											else if(valWord.ref == undefined){
												items.push( '<td><div class="elemWord context-menu-one box menu-1" divWordId="' + valWord.id + '" divWord="' + valWord.word + '" fromLang="' + fromLangSet + '" toLang="' + toLangSet + '">' + addPuncFront + valWord.word + addPunc + '</div><div class="elemTranslation">' + valWord.translation + '</div></td>' );
											}	
											items.push( "</td>" );
											
										});
									});
									items.push( '</tr>' );
									items.push( '<tr><td><br/></td></tr>' );
									items.push( '<tr><td><br/></td></tr>' );									
								});

							}

						});
						
						$("#contentElem").html("").html( items.join( "" ) );

					}			            	
	            	
	            }
	        });

						
		var toFromLanguages = {
				  "fromLang": fromLangSet,
				  "toLang": toLangSet
				}
		//Change the language select boxes languages.
		changeSelectLanguages(fromLangSet, toLangSet);		
		
		return toFromLanguages;
		
	}
		

	//jquery custom function found on http://medialize.github.io/jQuery-contextMenu/demo.html
	$(function() {
		$.contextMenu({
			selector : '.context-menu-one',
			callback : function(key, options) {
				if(key == "known"){
					knownWordFn(this.attr("toLang"), this.attr("divWordId"), this.attr("divWord"));
				}
				else if(key == "show"){
					unKnownWordFn(this.attr("toLang"), this.attr("divWordId"), this.attr("divWord"));
				}				
			},
			items : {
				"known" : {	name : "Dont show", icon : "" },
				"show" : { name : "Bring back", icon : "" },
				"seperator" : "---------",
				"quit" : { name : "Quit", icon : ""	}
			}
		});
	
		$('.context-menu-one').on('click', function(e) {
			console.log('clicked', this);
		})
	});
	      
