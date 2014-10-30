/**
 * Shows selected filter and disables it in the dropdown
 */

function add_filter(){
							
	var select = document.getElementById("add_filter_select");
	var field = select.value;
	var filter = document.getElementById(field);
	select.selectedIndex = 0;
	
	
	
	for (i=0; i<select.options.length; i++) {
        if (select.options[i].value == field) {
            select.options[i].disabled = true;
        }
    }
	
	if(filter.style.display == 'block') {
		filter.style.display = 'none';
	} else if(filter.style.display = 'none') {
		filter.style.display = 'inherit';	//-------------------------------------------- Used to be BLOCK. Change if buggy!
	}
	
	disableOptions(field);
	
}

/**
 * Hides selected filter and enables it in the dropdown
 */

function removeTransFilter(id){

	document.getElementById(id).style.display = 'none';
	clearFilterValue(id);
	var select = document.getElementById("add_filter_select");

	for (var i=0; i<select.options.length; i++) { 
        if (select.options[i].value == id) {
            select.options[i].disabled = false;
        }
    }
}

function clearFilterValue(rowId){

	if (rowId == 'sender_row') {
		clearDropDown("typeSender");
	} else if (rowId == 'reciever_row') {
		clearDropDown("typeReceiver");
	} else if (rowId == 'filename_row') {
		document.getElementById("typeFilename").value = '';
	} else if (rowId == 'status_row') {
		clearDropDown("typeStatus");
	} else if (rowId == 'protocol_row') {
		clearDropDown("typeProtocol");
	} else if (rowId == 'sites_row') {
		clearDropDown("eftSites");
	} else if (rowId == 'accounts_row') {
		clearDropDown("eftAccounts");
	} else if (rowId == 'eventrules_row') {
		clearDropDown("eftEventrules");
	} else if (rowId == 'remoteip_row') {
		document.getElementById("typeRemoteIP").value = '';
	} else if (rowId == 'username_row') {
		document.getElementById("typeUsername").value = '';
	} else if (rowId == 'title_row') {
		document.getElementById("typeTitle").value = '';
	}
}

function clearDropDown(dropdownId){
	
	
	var dropdown = document.getElementById(dropdownId);
	
	for ( var i = 0; i < dropdown.options.length; i++) {
		var dropTemp = dropdown.options[i];
		
		if(dropTemp.selected == true){
			dropTemp.selected = false;
		}
	}
	
	$('#' + dropdownId).multiselect('refresh');
	$('#' + dropdownId).multiselect().val('');
}

/**
 * Disable selected in the dropdown
 */

function disableDropdown(id){

	var select = document.getElementById("add_filter_select");

	for (i=0; i<select.options.length; i++) {
        if (select.options[i].value == id) {
            select.options[i].disabled = true;
        }    
    }
}

function disableOptions(field) {
	
	var select = document.getElementById("add_filter_select");
	
	if (field == 'sender_row') {
		for (i=0; i<select.options.length; i++) {
			if (select.options[i].value == 'accounts_row' | select.options[i].value == 'eventrules_row') {
	            select.options[i].disabled = true;
	        }
		}
	}
	
	if (field == 'reciever_row') {
		for (i=0; i<select.options.length; i++) {
			if (select.options[i].value == 'accounts_row' | select.options[i].value == 'eventrules_row') {
	            select.options[i].disabled = true;
	        }
		}
	}
	
	if (field == 'accounts_row') {
		for (i=0; i<select.options.length; i++) {
			if (select.options[i].value == 'reciever_row' | select.options[i].value == 'sender_row') {
	            select.options[i].disabled = true;
	        }
		}
	}
	
	if (field == 'eventrules_row') {
		for (i=0; i<select.options.length; i++) {
			if (select.options[i].value == 'reciever_row' | select.options[i].value == 'sender_row') {
	            select.options[i].disabled = true;
	        }
		}
	}
}
/**
 * Check witch filter is in use
 */

function checkFilter() {
			 		
	var sender_row = document.getElementById("sender_row");
	var reciever_row = document.getElementById("reciever_row");
	var filename_row = document.getElementById("filename_row");
	var status_row = document.getElementById("status_row");
	var protocol_row = document.getElementById("protocol_row");
	var sites_row = document.getElementById("sites_row");
	var user_row = document.getElementById("accounts_row");
	var event_row = document.getElementById("eventrules_row");
	var remoteip_row = document.getElementById("remoteip_row");
	var username_row = document.getElementById("username_row");
	
	var sender = document.getElementById("typeSender");
	var reciever = document.getElementById("typeReceiver");
	var filename = document.getElementById("typeFilename");
	var status = document.getElementById("typeStatus");
	var protocol = document.getElementById("typeProtocol");
	var sites = document.getElementById("eftSites");
	var user = document.getElementById("eftAccounts");
	var event = document.getElementById("eftEventrules");
	var remoteIp = document.getElementById("typeRemoteIP");
	var username = document.getElementById("typeUsername");
	
	if (sender_row != null) {
		if (sender != null && sender.value == '') {
			sender_row.style.display = 'none';
		} else {
			sender_row.style.display = 'block';
			disableDropdown('sender_row');
			disableOptions('sender_row');
		}
	}
	
	if (remoteip_row != null) {
		if (remoteIp != null && remoteIp.value == '') {
			remoteip_row.style.display = 'none';
		} else {
			remoteip_row.style.display = 'block';
			disableDropdown('remoteip_row');
		}
	}
	
	if (username_row != null) {
		if (username != null && username.value == '') {
			username_row.style.display = 'none';
		} else {
			username_row.style.display = 'block';
			disableDropdown('username_row');
		}
	}

	if (reciever_row != null) {
		if (reciever != null && reciever.value == '') {
			reciever_row.style.display = 'none';
		} else {
			reciever_row.style.display = 'block';
			disableDropdown('reciever_row');
			disableOptions('reciever_row');
		}
	}
	
	if (filename_row != null) {
		if (filename != null && filename.value == '') {
			filename_row.style.display = 'none';
		} else {
			filename_row.style.display = 'block';
			disableDropdown('filename_row');
		}
	}
	
	if (status_row != null) {
		if (status != null && status.value == '') {
			status_row.style.display = 'none';
		} else {
			status_row.style.display = 'block';
			disableDropdown('status_row');
		}
	}
	
	if (protocol_row != null) {
		if (protocol != null && (protocol.value == '' || protocol.value == 'Select Protocol')) {
			protocol_row.style.display = 'none';
		} else {
			protocol_row.style.display = 'block';
			disableDropdown('protocol_row');
		}
	}
	
	if (sites_row != null) {
		if (sites != null && sites.selectedIndex == -1) {
			sites_row.style.display = 'none';
		} else {
			sites_row.style.display = 'block';
			disableDropdown('sites_row');
		}
	}
	
	if (user_row != null) {
		if (user != null && user.selectedIndex == -1) {
			user_row.style.display = 'none';
		} else {
			user_row.style.display = 'block';
			disableDropdown('accounts_row');
			disableOptions('accounts_row');
		}
	}
	
	if (event_row != null) {
		if (event != null && event.selectedIndex == -1) {
			event_row.style.display = 'none';
		} else {
			event_row.style.display = 'block';
			disableDropdown('eventrules_row');
			disableOptions('eventrules_row');
		}
	}
	
}