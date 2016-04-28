﻿/*
Copyright (c) 2003-2009, CKSource - Frederico Knabben. All rights reserved.
For licensing, see LICENSE.html or http://ckeditor.com/license
*/

/**
 * @fileOverview Defines the {@link CKEDITOR.lang} object, for the
 * Italian language.
 */

/**#@+
   @type String
   @example
*/

/**
 * Constains the dictionary of language entries.
 * @namespace
 */
CKEDITOR.lang['it'] =
{
	/**
	 * The language reading direction. Possible values are "rtl" for
	 * Right-To-Left languages (like Arabic) and "ltr" for Left-To-Right
	 * languages (like English).
	 * @default 'ltr'
	 */
	dir : 'ltr',

	/*
	 * Screenreader titles. Please note that screenreaders are not always capable
	 * of reading non-English words. So be careful while translating it.
	 */
	editorTitle		: 'Rich text editor, %1', // MISSING

	// Toolbar buttons without dialogs.
	source			: 'Codice Sorgente',
	newPage			: 'Nuova pagina vuota',
	save			: 'Salva',
	preview			: 'Anteprima',
	cut				: 'Taglia',
	copy			: 'Copia',
	paste			: 'Incolla',
	print			: 'Stampa',
	underline		: 'Sottolineato',
	bold			: 'Grassetto',
	italic			: 'Corsivo',
	selectAll		: 'Seleziona tutto',
	removeFormat	: 'Elimina formattazione',
	strike			: 'Barrato',
	subscript		: 'Pedice',
	superscript		: 'Apice',
	horizontalrule	: 'Inserisci riga orizzontale',
	pagebreak		: 'Inserisci interruzione di pagina',
	unlink			: 'Elimina collegamento',
	undo			: 'Annulla',
	redo			: 'Ripristina',

	// Common messages and labels.
	common :
	{
		browseServer	: 'Cerca sul server',
		url				: 'URL',
		protocol		: 'Protocollo',
		upload			: 'Carica',
		uploadSubmit	: 'Invia al server',
		image			: 'Immagine',
		flash			: 'Oggetto Flash',
		form			: 'Modulo',
		checkbox		: 'Checkbox',
		radio		: 'Radio Button',
		textField		: 'Campo di testo',
		textarea		: 'Area di testo',
		hiddenField		: 'Campo nascosto',
		button			: 'Bottone',
		select	: 'Menu di selezione',
		imageButton		: 'Bottone immagine',
		notSet			: '<non impostato>',
		id				: 'Id',
		name			: 'Nome',
		langDir			: 'Direzione scrittura',
		langDirLtr		: 'Da Sinistra a Destra (LTR)',
		langDirRtl		: 'Da Destra a Sinistra (RTL)',
		langCode		: 'Codice Lingua',
		longDescr		: 'URL descrizione estesa',
		cssClass		: 'Nome classe CSS',
		advisoryTitle	: 'Titolo',
		cssStyle		: 'Stile',
		ok				: 'OK',
		cancel			: 'Annulla',
		generalTab		: 'Generale',
		advancedTab		: 'Avanzate',
		validateNumberFailed	: 'Il valore inserito non è un numero.',
		confirmNewPage	: 'Ogni modifica non salvata sarà persa. Sei sicuro di voler caricare una nuova pagina?',
		confirmCancel	: 'Alcune delle opzioni sono state cambiate. Sei sicuro di voler chiudere la finestra di dialogo?',

		// Put the voice-only part of the label in the span.
		unavailable		: '%1<span class="cke_accessibility">, non disponibile</span>'
	},

	// Special char dialog.
	specialChar		:
	{
		toolbar		: 'Inserisci carattere speciale',
		title		: 'Seleziona carattere speciale'
	},

	// Link dialog.
	link :
	{
		toolbar		: 'Inserisci/Modifica collegamento',
		menu		: 'Modifica collegamento',
		title		: 'Collegamento',
		info		: 'Informazioni collegamento',
		target		: 'Destinazione',
		upload		: 'Carica',
		advanced	: 'Avanzate',
		type		: 'Tipo di Collegamento',
		toAnchor	: 'Ancora nella pagina',
		toEmail		: 'E-Mail',
		target		: 'Destinazione',
		targetNotSet	: '<non impostato>',
		targetFrame	: '<riquadro>',
		targetPopup	: '<finestra popup>',
		targetNew	: 'Nuova finestra (_blank)',
		targetTop	: 'Finestra superiore (_top)',
		targetSelf	: 'Stessa finestra (_self)',
		targetParent	: 'Finestra padre (_parent)',
		targetFrameName	: 'Nome del riquadro di destinazione',
		targetPopupName	: 'Nome finestra popup',
		popupFeatures	: 'Caratteristiche finestra popup',
		popupResizable	: 'Ridimensionabile',
		popupStatusBar	: 'Barra di stato',
		popupLocationBar	: 'Barra degli indirizzi',
		popupToolbar	: 'Barra degli strumenti',
		popupMenuBar	: 'Barra del menu',
		popupFullScreen	: 'A tutto schermo (IE)',
		popupScrollBars	: 'Barre di scorrimento',
		popupDependent	: 'Dipendente (Netscape)',
		popupWidth		: 'Larghezza',
		popupLeft		: 'Posizione da sinistra',
		popupHeight		: 'Altezza',
		popupTop		: 'Posizione dall\'alto',
		id				: 'Id',
		langDir			: 'Direzione scrittura',
		langDirNotSet	: '<non impostato>',
		langDirLTR		: 'Da Sinistra a Destra (LTR)',
		langDirRTL		: 'Da Destra a Sinistra (RTL)',
		acccessKey		: 'Scorciatoia<br />da tastiera',
		name			: 'Nome',
		langCode		: 'Direzione scrittura',
		tabIndex		: 'Ordine di tabulazione',
		advisoryTitle	: 'Titolo',
		advisoryContentType	: 'Tipo della risorsa collegata',
		cssClasses		: 'Nome classe CSS',
		charset			: 'Set di caretteri della risorsa collegata',
		styles			: 'Stile',
		selectAnchor	: 'Scegli Ancora',
		anchorName		: 'Per Nome',
		anchorId		: 'Per id elemento',
		emailAddress	: 'Indirizzo E-Mail',
		emailSubject	: 'Oggetto del messaggio',
		emailBody		: 'Corpo del messaggio',
		noAnchors		: '(Nessuna ancora disponibile nel documento)',
		noUrl			: 'Devi inserire l\'URL del collegamento',
		noEmail			: 'Devi inserire un\'indirizzo e-mail'
	},

	// Anchor dialog
	anchor :
	{
		toolbar		: 'Inserisci/Modifica Ancora',
		menu		: 'Proprietà ancora',
		title		: 'Proprietà ancora',
		name		: 'Nome ancora',
		errorName	: 'Inserici il nome dell\'ancora'
	},

	// Find And Replace Dialog
	findAndReplace :
	{
		title				: 'Cerca e Sostituisci',
		find				: 'Trova',
		replace				: 'Sostituisci',
		findWhat			: 'Trova:',
		replaceWith			: 'Sostituisci con:',
		notFoundMsg			: 'L\'elemento cercato non è stato trovato.',
		matchCase			: 'Maiuscole/minuscole',
		matchWord			: 'Solo parole intere',
		matchCyclic			: 'Ricerca ciclica',
		replaceAll			: 'Sostituisci tutto',
		replaceSuccessMsg	: '%1 occorrenza(e) sostituite.'
	},

	// Table Dialog
	table :
	{
		toolbar		: 'Tabella',
		title		: 'Proprietà tabella',
		menu		: 'Proprietà tabella',
		deleteTable	: 'Cancella Tabella',
		rows		: 'Righe',
		columns		: 'Colonne',
		border		: 'Dimensione bordo',
		align		: 'Allineamento',
		alignNotSet	: '<non impostato>',
		alignLeft	: 'Sinistra',
		alignCenter	: 'Centrato',
		alignRight	: 'Destra',
		width		: 'Larghezza',
		widthPx		: 'pixel',
		widthPc		: 'percento',
		height		: 'Altezza',
		cellSpace	: 'Spaziatura celle',
		cellPad		: 'Padding celle',
		caption		: 'Intestazione',
		summary		: 'Indice',
		headers		: 'Intestazione',
		headersNone		: 'Nessuna',
		headersColumn	: 'Prima Colonna',
		headersRow		: 'Prima Riga',
		headersBoth		: 'Entrambe',
		invalidRows		: 'Il numero di righe dev\'essere un numero maggiore di 0.',
		invalidCols		: 'Il numero di colonne dev\'essere un numero maggiore di 0.',
		invalidBorder	: 'La dimensione del bordo dev\'essere un numero.',
		invalidWidth	: 'La larghezza della tabella dev\'essere un numero.',
		invalidHeight	: 'L\'altezza della tabella dev\'essere un numero.',
		invalidCellSpacing	: 'La spaziatura tra le celle dev\'essere un numero.',
		invalidCellPadding	: 'Il pagging delle celle dev\'essere un numero',

		cell :
		{
			menu			: 'Cella',
			insertBefore	: 'Inserisci Cella Prima',
			insertAfter		: 'Inserisci Cella Dopo',
			deleteCell		: 'Elimina celle',
			merge			: 'Unisce celle',
			mergeRight		: 'Unisci a Destra',
			mergeDown		: 'Unisci in Basso',
			splitHorizontal	: 'Dividi Cella Orizzontalmente',
			splitVertical	: 'Dividi Cella Verticalmente',
			title			: 'Proprietà della cella',
			cellType		: 'Tipo di cella',
			rowSpan			: 'Su più righe',
			colSpan			: 'Su più colonne',
			wordWrap		: 'Ritorno a capo',
			hAlign			: 'Allineamento orizzontale',
			vAlign			: 'Allineamento verticale',
			alignTop		: 'In Alto',
			alignMiddle		: 'Al Centro',
			alignBottom		: 'In Basso',
			alignBaseline	: 'Linea Base',
			bgColor			: 'Colore di Sfondo',
			borderColor		: 'Colore del Bordo',
			data			: 'Dati',
			header			: 'Intestazione',
			yes				: 'Si',
			no				: 'No',
			invalidWidth	: 'La larghezza della cella dev\'essere un numero.',
			invalidHeight	: 'L\'altezza della cella dev\'essere un numero.',
			invalidRowSpan	: 'Il numero di righe dev\'essere un numero intero.',
			invalidColSpan	: 'Il numero di colonne dev\'essere un numero intero.',
			chooseColor : 'Choose' // MISSING
		},

		row :
		{
			menu			: 'Riga',
			insertBefore	: 'Inserisci Riga Prima',
			insertAfter		: 'Inserisci Riga Dopo',
			deleteRow		: 'Elimina righe'
		},

		column :
		{
			menu			: 'Colonna',
			insertBefore	: 'Inserisci Colonna Prima',
			insertAfter		: 'Inserisci Colonna Dopo',
			deleteColumn	: 'Elimina colonne'
		}
	},

	// Button Dialog.
	button :
	{
		title		: 'Proprietà bottone',
		text		: 'Testo (Value)',
		type		: 'Tipo',
		typeBtn		: 'Bottone',
		typeSbm		: 'Invio',
		typeRst		: 'Annulla'
	},

	// Checkbox and Radio Button Dialogs.
	checkboxAndRadio :
	{
		checkboxTitle : 'Proprietà checkbox',
		radioTitle	: 'Proprietà radio button',
		value		: 'Valore',
		selected	: 'Selezionato'
	},

	// Form Dialog.
	form :
	{
		title		: 'Proprietà modulo',
		menu		: 'Proprietà modulo',
		action		: 'Azione',
		method		: 'Metodo',
		encoding	: 'Codifica',
		target		: 'Destinazione',
		targetNotSet	: '<non impostato>',
		targetNew	: 'Nuova finestra (_blank)',
		targetTop	: 'Finestra superiore (_top)',
		targetSelf	: 'Stessa finestra (_self)',
		targetParent	: 'Finestra padre (_parent)'
	},

	// Select Field Dialog.
	select :
	{
		title		: 'Proprietà menu di selezione',
		selectInfo	: 'Info',
		opAvail		: 'Opzioni disponibili',
		value		: 'Valore',
		size		: 'Dimensione',
		lines		: 'righe',
		chkMulti	: 'Permetti selezione multipla',
		opText		: 'Testo',
		opValue		: 'Valore',
		btnAdd		: 'Aggiungi',
		btnModify	: 'Modifica',
		btnUp		: 'Su',
		btnDown		: 'Gi',
		btnSetValue : 'Imposta come predefinito',
		btnDelete	: 'Rimuovi'
	},

	// Textarea Dialog.
	textarea :
	{
		title		: 'Proprietà area di testo',
		cols		: 'Colonne',
		rows		: 'Righe'
	},

	// Text Field Dialog.
	textfield :
	{
		title		: 'Proprietà campo di testo',
		name		: 'Nome',
		value		: 'Valore',
		charWidth	: 'Larghezza',
		maxChars	: 'Numero massimo di caratteri',
		type		: 'Tipo',
		typeText	: 'Testo',
		typePass	: 'Password'
	},

	// Hidden Field Dialog.
	hidden :
	{
		title	: 'Proprietà campo nascosto',
		name	: 'Nome',
		value	: 'Valore'
	},

	// Image Dialog.
	image :
	{
		title		: 'Proprietà immagine',
		titleButton	: 'Proprietà bottone immagine',
		menu		: 'Proprietà immagine',
		infoTab	: 'Informazioni immagine',
		btnUpload	: 'Invia al server',
		url		: 'URL',
		upload	: 'Carica',
		alt		: 'Testo alternativo',
		width		: 'Larghezza',
		height	: 'Altezza',
		lockRatio	: 'Blocca rapporto',
		resetSize	: 'Reimposta dimensione',
		border	: 'Bordo',
		hSpace	: 'HSpace',
		vSpace	: 'VSpace',
		align		: 'Allineamento',
		alignLeft	: 'Sinistra',
		alignAbsBottom: 'In basso assoluto',
		alignAbsMiddle: 'Centrato assoluto',
		alignBaseline	: 'Linea base',
		alignBottom	: 'In Basso',
		alignMiddle	: 'Centrato',
		alignRight	: 'Destra',
		alignTextTop	: 'In alto al testo',
		alignTop	: 'In Alto',
		preview	: 'Anteprima',
		alertUrl	: 'Devi inserire l\'URL per l\'immagine',
		linkTab	: 'Collegamento',
		button2Img	: 'Vuoi trasformare il bottone immagine selezionato in un\'immagine semplice?',
		img2Button	: 'Vuoi trasferomare l\'immagine selezionata in un bottone immagine?',
		urlMissing : 'Image source URL is missing.' // MISSING
	},

	// Flash Dialog
	flash :
	{
		properties		: 'Proprietà Oggetto Flash',
		propertiesTab	: 'Proprietà',
		title		: 'Proprietà Oggetto Flash',
		chkPlay		: 'Avvio Automatico',
		chkLoop		: 'Riavvio automatico',
		chkMenu		: 'Abilita Menu di Flash',
		chkFull		: 'Permetti la modalità tutto schermo',
 		scale		: 'Ridimensiona',
		scaleAll		: 'Mostra Tutto',
		scaleNoBorder	: 'Senza Bordo',
		scaleFit		: 'Dimensione Esatta',
		access			: 'Accesso Script',
		accessAlways	: 'Sempre',
		accessSameDomain	: 'Solo stesso dominio',
		accessNever	: 'Mai',
		align		: 'Allineamento',
		alignLeft	: 'Sinistra',
		alignAbsBottom: 'In basso assoluto',
		alignAbsMiddle: 'Centrato assoluto',
		alignBaseline	: 'Linea base',
		alignBottom	: 'In Basso',
		alignMiddle	: 'Centrato',
		alignRight	: 'Destra',
		alignTextTop	: 'In alto al testo',
		alignTop	: 'In Alto',
		quality		: 'Qualità',
		qualityBest		 : 'Massima',
		qualityHigh		 : 'Alta',
		qualityAutoHigh	 : 'Alta Automatica',
		qualityMedium	 : 'Intermedia',
		qualityAutoLow	 : 'Bassa Automatica',
		qualityLow		 : 'Bassa',
		windowModeWindow	 : 'Finestra',
		windowModeOpaque	 : 'Opaca',
		windowModeTransparent	 : 'Trasparente',
		windowMode	: 'Modalità finestra',
		flashvars	: 'Variabili per Flash',
		bgcolor	: 'Colore sfondo',
		width	: 'Larghezza',
		height	: 'Altezza',
		hSpace	: 'HSpace',
		vSpace	: 'VSpace',
		validateSrc : 'Devi inserire l\'URL del collegamento',
		validateWidth : 'La Larghezza dev\'essere un numero',
		validateHeight : 'L\'altezza dev\'essere un numero',
		validateHSpace : 'L\'HSpace dev\'essere un numero.',
		validateVSpace : 'Il VSpace dev\'essere un numero.'
	},

	// Speller Pages Dialog
	spellCheck :
	{
		toolbar			: 'Correttore ortografico',
		title			: 'Controllo ortografico',
		notAvailable	: 'Il servizio non è momentaneamente disponibile.',
		errorLoading	: 'Errore nel caricamento dell\'host col servizio applicativo: %s.',
		notInDic		: 'Non nel dizionario',
		changeTo		: 'Cambia in',
		btnIgnore		: 'Ignora',
		btnIgnoreAll	: 'Ignora tutto',
		btnReplace		: 'Cambia',
		btnReplaceAll	: 'Cambia tutto',
		btnUndo			: 'Annulla',
		noSuggestions	: '- Nessun suggerimento -',
		progress		: 'Controllo ortografico in corso',
		noMispell		: 'Controllo ortografico completato: nessun errore trovato',
		noChanges		: 'Controllo ortografico completato: nessuna parola cambiata',
		oneChange		: 'Controllo ortografico completato: 1 parola cambiata',
		manyChanges		: 'Controllo ortografico completato: %1 parole cambiate',
		ieSpellDownload	: 'Contollo ortografico non installato. Lo vuoi scaricare ora?'
	},

	smiley :
	{
		toolbar	: 'Emoticon',
		title	: 'Inserisci emoticon'
	},

	elementsPath :
	{
		eleTitle : '%1 elemento'
	},

	numberedlist : 'Elenco numerato',
	bulletedlist : 'Elenco puntato',
	indent : 'Aumenta rientro',
	outdent : 'Riduci rientro',

	justify :
	{
		left : 'Allinea a sinistra',
		center : 'Centra',
		right : 'Allinea a destra',
		block : 'Giustifica'
	},

	blockquote : 'Citazione',

	clipboard :
	{
		title		: 'Incolla',
		cutError	: 'Le impostazioni di sicurezza del browser non permettono di tagliare automaticamente il testo. Usa la tastiera (Ctrl+X).',
		copyError	: 'Le impostazioni di sicurezza del browser non permettono di copiare automaticamente il testo. Usa la tastiera (Ctrl+C).',
		pasteMsg	: 'Incolla il testo all\'interno dell\'area sottostante usando la scorciatoia di tastiere (<STRONG>Ctrl+V</STRONG>) e premi <STRONG>OK</STRONG>.',
		securityMsg	: 'A causa delle impostazioni di sicurezza del browser,l\'editor non è in grado di accedere direttamente agli appunti. E\' pertanto necessario incollarli di nuovo in questa finestra.'
	},

	pastefromword :
	{
		toolbar : 'Incolla da Word',
		title : 'Incolla da Word',
		advice : 'Incolla il testo all\'interno dell\'area sottostante usando la scorciatoia di tastiere (<STRONG>Ctrl+V</STRONG>) e premi <STRONG>OK</STRONG>.',
		ignoreFontFace : 'Ignora le definizioni di Font',
		removeStyle : 'Rimuovi le definizioni di Stile'
	},

	pasteText :
	{
		button : 'Incolla come testo semplice',
		title : 'Incolla come testo semplice'
	},

	templates :
	{
		button : 'Modelli',
		title : 'Contenuto dei modelli',
		insertOption: 'Cancella il contenuto corrente',
		selectPromptMsg: 'Seleziona il modello da aprire nell\'editor<br />(il contenuto attuale verrà eliminato):',
		emptyListMsg : '(Nessun modello definito)'
	},

	showBlocks : 'Visualizza Blocchi',

	stylesCombo :
	{
		label : 'Stile',
		voiceLabel : 'Stili',
		panelVoiceLabel : 'Seleziona uno stile',
		panelTitle1 : 'Stili per blocchi',
		panelTitle2 : 'Stili in linea',
		panelTitle3 : 'Stili per oggetti'
	},

	format :
	{
		label : 'Formato',
		voiceLabel : 'Formato',
		panelTitle : 'Formato',
		panelVoiceLabel : 'Seleziona il formato per paragrafo',

		tag_p : 'Normale',
		tag_pre : 'Formattato',
		tag_address : 'Indirizzo',
		tag_h1 : 'Titolo 1',
		tag_h2 : 'Titolo 2',
		tag_h3 : 'Titolo 3',
		tag_h4 : 'Titolo 4',
		tag_h5 : 'Titolo 5',
		tag_h6 : 'Titolo 6',
		tag_div : 'Paragrafo (DIV)'
	},

	div :
	{
		title				: 'Create Div Container', // MISSING
		toolbar				: 'Create Div Container', // MISSING
		cssClassInputLabel	: 'Stylesheet Classes', // MISSING
		styleSelectLabel	: 'Style', // MISSING
		IdInputLabel		: 'Id', // MISSING
		languageCodeInputLabel	: ' Language Code', // MISSING
		inlineStyleInputLabel	: 'Inline Style', // MISSING
		advisoryTitleInputLabel	: 'Advisory Title', // MISSING
		langDirLabel		: 'Language Direction', // MISSING
		langDirLTRLabel		: 'Left to Right (LTR)', // MISSING
		langDirRTLLabel		: 'Right to Left (RTL)', // MISSING
		edit				: 'Edit Div', // MISSING
		remove				: 'Remove Div' // MISSING
  	},

	font :
	{
		label : 'Font',
		voiceLabel : 'Font',
		panelTitle : 'Font',
		panelVoiceLabel : 'Seleziona un font'
	},

	fontSize :
	{
		label : 'Dimensione',
		voiceLabel : 'Dimensione Font',
		panelTitle : 'Dimensione',
		panelVoiceLabel : 'Seleziona una dimensione font'
	},

	colorButton :
	{
		textColorTitle : 'Colore testo',
		bgColorTitle : 'Colore sfondo',
		auto : 'Automatico',
		more : 'Altri colori...'
	},

	colors :
	{
		'000' : 'Black',
		'800000' : 'Maroon',
		'8B4513' : 'Saddle Brown',
		'2F4F4F' : 'Dark Slate Gray',
		'008080' : 'Teal',
		'000080' : 'Navy',
		'4B0082' : 'Indigo',
		'696969' : 'Dim Gray',
		'B22222' : 'Fire Brick',
		'A52A2A' : 'Brown',
		'DAA520' : 'Golden Rod',
		'006400' : 'Dark Green',
		'40E0D0' : 'Turquoise',
		'0000CD' : 'Medium Blue',
		'800080' : 'Purple',
		'808080' : 'Gray',
		'F00' : 'Red',
		'FF8C00' : 'Dark Orange',
		'FFD700' : 'Gold',
		'008000' : 'Green',
		'0FF' : 'Cyan',
		'00F' : 'Blue',
		'EE82EE' : 'Violet',
		'A9A9A9' : 'Dark Gray',
		'FFA07A' : 'Light Salmon',
		'FFA500' : 'Orange',
		'FFFF00' : 'Yellow',
		'00FF00' : 'Lime',
		'AFEEEE' : 'Pale Turquoise',
		'ADD8E6' : 'Light Blue',
		'DDA0DD' : 'Plum',
		'D3D3D3' : 'Light Grey',
		'FFF0F5' : 'Lavender Blush',
		'FAEBD7' : 'Antique White',
		'FFFFE0' : 'Light Yellow',
		'F0FFF0' : 'Honeydew',
		'F0FFFF' : 'Azure',
		'F0F8FF' : 'Alice Blue',
		'E6E6FA' : 'Lavender',
		'FFF' : 'White'
	},

	scayt :
	{
		title : 'Controllo Ortografico Mentre Scrivi',
		enable : 'Abilita COMS',
		disable : 'Disabilita COMS',
		about : 'About COMS',
		toggle : 'Inverti abilitazione SCOMS',
		options : 'Opzioni',
		langs : 'Lingue',
		moreSuggestions : 'Altri suggerimenti',
		ignore : 'Ignora',
		ignoreAll : 'Ignora tutti',
		addWord : 'Aggiungi Parola',
		emptyDic : 'Il nome del dizionario non può essere vuoto.',
		optionsTab : 'Opzioni',
		languagesTab : 'Lingue',
		dictionariesTab : 'Dizionari',
		aboutTab : 'About'
	},

	about :
	{
		title : 'About CKEditor',
		dlgTitle : 'About CKEditor',
		moreInfo : 'Per le informazioni sulla licenza si prega di visitare il nostro sito:',
		copy : 'Copyright &copy; $1. Tutti i diritti riservati.'
	},

	maximize : 'Massimizza',
	minimize : 'Minimize', // MISSING

	fakeobjects :
	{
		anchor : 'Ancora',
		flash : 'Animazione Flash',
		div : 'Interruzione di Pagina',
		unknown : 'Oggetto sconosciuto'
	},

	resize : 'Trascina per ridimensionare',

	colordialog :
	{
		title : 'Select color', // MISSING
		highlight : 'Highlight', // MISSING
		selected : 'Selected', // MISSING
		clear : 'Clear' // MISSING
	}
};
