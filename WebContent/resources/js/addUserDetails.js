$(document).ready(function() {
	var isValidAll;
	
	//Event Handler click for seach button
    $("#addUser").click(function() {
    	isValidAll = true;
    	verifyFullname();
    	verifyEmail();
    	verifyDob();
    	verifyStreet();
    	verifyCity();
    	verifyState();
    	verifyZipCode();
    });
    
    function verifyFullname() {
    	if($("#fullName").value() == "") {
    		isValidAll = false;
    		$("#errorsFullName").text("This field should not be empty");
    		$("#errorsFullName").show();
    	} else {
    		$("#errorsFullName").hide();
    	}
    }
    
    function verifyEmail() {
    	var re = new RegExp("^[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");
    	
    	if($("#email").value() == "") {
    		isValidAll = false;
    		$("#errorsEmail").text("This field should not be empty");
    		$("#errorsEmail").show();
    	} else if(re.test($("email").value())) {
    		isValidAll = false;
    		$("#errorsEmail").text("Email should be correct format");
    		$("#errorsEmail").show();
    	} else {
    		$("#errorsEmail").hide();
    	}
    }
    
    
    function verifyDob() {
    	var re = new RegExp("^(0?[1-9]|1[012])[\/\-](0?[1-9]|[12][0-9]|3[01])[\/\-]\d{4}$");
    	
    	if($("#dob").value() == "") {
    		isValidAll = false;
    		$("#errorsDob").text("This field should not be empty");
    		$("#errorsDob").show();
    	} else if(re.test($("email").value())) {
    		isValidAll = false;
    		$("#errorsDob").text("Dob should be correct format");
    		$("#errorsDob").show();
    	} else {
    		$("#errorsDob").hide();
    	}
    }
    
    function verifyStreet() {
    	if($("#street").value() == "") {
    		isValidAll = false;
    		$("#errorsStreet").text("This field should not be empty");
    		$("#errorsStreet").show();
    	} else {
    		$("#errorsStreet").hide();
    	}
    }
    
    function verifyCity() {
    	if($("#city").value() == "") {
    		isValidAll = false;
    		$("#errorsCity").text("This field should not be empty");
    		$("#errorsCity").show();
    	} else {
    		$("#errorsCity").hide();
    	}
    }
    
    function verifyState() {
    	var re = new RegExp("\w{2}$");
    	
    	if($("#state").value() == "") {
    		isValidAll = false;
    		$("#errorsState").text("This field should not be empty");
    		$("#errorsState").show();
    	} else if(re.test($("email").value())) {
    		isValidAll = false;
    		$("#errorsState").text("Should enter 2 characters");
    		$("#errorsState").show();
    	} else {
    		$("#errorsState").hide();
    	}
    }
    
    function verifyZipCode() {
    	var re = new RegExp("\d{5}$");
    	
    	if($("#zipCode").value() == "") {
    		isValidAll = false;
    		$("#errorsZipCode").text("This field should not be empty");
    		$("#errorsZipCode").show();
    	} else if(re.test($("email").value())) {
    		isValidAll = false;
    		$("#errorsZipCode").text("Should enter 5 numbers");
    		$("#errorsZipCode").show();
    	} else {
    		$("#errorsZipCode").hide();
    	}
    }
    
    
});