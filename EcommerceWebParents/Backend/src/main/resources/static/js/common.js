/////////////////////////////////////////////This is for user_form.html///////////////////////////////////////////////////////
$(document).ready(function() {
	$("#buttonCancel").on("click", function() {
		window.location = moduleURL;
	});
	
	$("#fileImage").change(function() {
		if (!checkFileSize(this)) {
			return;
		}
		
		showImageThumbnail(this);				
	});
});

function showImageThumbnail(fileInput) {
	var file = fileInput.files[0];
	var reader = new FileReader();
	reader.onload = function(e) {
		$("#thumbnail").attr("src", e.target.result);
	};
	
	reader.readAsDataURL(file);
}

function checkFileSize(fileInput) {
	fileSize = fileInput.files[0].size;
	
	if (fileSize > MAX_FILE_SIZE) {
		fileInput.setCustomValidity("You must choose an image less than " + MAX_FILE_SIZE + " bytes!");
		fileInput.reportValidity();
		
		return false;
	} else {
		fileInput.setCustomValidity("");
		
		return true;
	}	
}

function showModalDialog(title, message) {
	$("#modalTitle").text(title);
	$("#modalBody").text(message);
	$("#modalDialog").modal();
}

function showErrorModal(message) {
	showModalDialog("Error", message);
}

function showWarningModal(message) {
	showModalDialog("Warning", message);
}



////////////////////////////////////////////////////Navigation dropdown Logout for all pages///////////////////////////////
$(document).ready(function() {
	$("#logoutLink").on("click", function(e) {
		e.preventDefault();
		document.logoutForm.submit();
	});
	
	customizeDropDownMenu();
	customizeTabs();
});

function customizeDropDownMenu() {
	$(".navbar .dropdown").hover(
		function() {
			$(this).find('.dropdown-menu').first().stop(true, true).delay(250).slideDown();
		},
		function() {
			$(this).find('.dropdown-menu').first().stop(true, true).delay(100).slideUp();
		}
	);
	
	$(".dropdown > a").click(function() {
		location.href = this.href;
	});
}

function customizeTabs() {
	// Javascript to enable link to tab
	var url = document.location.toString();
	if (url.match('#')) {
	    $('.nav-tabs a[href="#' + url.split('#')[1] + '"]').tab('show');
	} 

	// Change hash for page-reload
	$('.nav-tabs a').on('shown.bs.tab', function (e) {
	    window.location.hash = e.target.hash;
	})	
}



////////////////////////////////////////////////////////////This is for users.html//////////////////////////////////////////////
$(document).ready(function(){
	$(".link-delete").on("click", function(e){
		e.preventDefault();
		link = $(this);
		//alert($(this).attr("href"));
		userId = link.attr("userId");
		$("#yesButton").attr("href", link.attr("href"));
		$("#confirmText").text("Are you sure you want to delete this user ID " + userId + "?");
		$("#confirmModal").modal();
	});
});