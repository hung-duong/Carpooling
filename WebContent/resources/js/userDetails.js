$(document).ready(function() {
	var isValidAll;
	
	//Event Handler click for seach button
    $("#userForm").click(function() {
    	isValidAll = true;
    	verifyFullname();
    	verifyEmail();
    	verifyDob();
    	verifyStreet();
    	verifyCity();
    	verifyState();
    	verifyZipCode();
    	
    	if(!isValidAll) 
    		return false;
    });
    
    function verifyFullname() {
    	if($("#fullName").val() == "") {
    		isValidAll = false;
    		$("#errorsFullName").text("This field should not be empty");
    		$("#errorsFullName").show();
    	} else {
    		$("#errorsFullName").hide();
    	}
    }
    
    function verifyEmail() {
    	var re = new RegExp("^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\.[a-zA-Z0.9]+$");
    	
    	if($("#email").val() == "") {
    		isValidAll = false;
    		$("#errorsEmail").text("This field should not be empty");
    		$("#errorsEmail").show();
    	} else if(!re.test($("#email").val())) {
    		isValidAll = false;
    		$("#errorsEmail").text("Email should be correct format");
    		$("#errorsEmail").show();
    	} else {
    		$("#errorsEmail").hide();
    	}
    }
    
    
    function verifyDob() {
    	var re = new RegExp("^(0?[1-9]|1[012])/(0?[1-9]|[12][0-9]|3[01])/[0-9]{4}$");
    	
    	if($("#dob").val() == "") {
    		isValidAll = false;
    		$("#errorsDob").text("This field should not be empty");
    		$("#errorsDob").show();
    	} else if(!re.test($("#dob").val())) {
    		isValidAll = false;
    		$("#errorsDob").text("Dob should be correct format");
    		$("#errorsDob").show();
    	} else {
    		var age = getAge($("#dob").val());
    		if(age <= 18) {
    			isValidAll = false;
        		$("#errorsDob").text("You should be greater then 18 years old");
        		$("#errorsDob").show();
    		} else {
    			$("#errorsDob").hide();
    		}
    	}
    }
    
    function getAge(dateString) {
        var today = new Date();
        var birthDate = new Date(dateString);
        var age = today.getFullYear() - birthDate.getFullYear();
        var m = today.getMonth() - birthDate.getMonth();
        if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
            age--;
        }
        return age;
    }
    
    function verifyStreet() {
    	if($("#street").val() == "") {
    		isValidAll = false;
    		$("#errorsStreet").text("This field should not be empty");
    		$("#errorsStreet").show();
    	} else {
    		$("#errorsStreet").hide();
    	}
    }
    
    function verifyCity() {
    	if($("#city").val() == "") {
    		isValidAll = false;
    		$("#errorsCity").text("This field should not be empty");
    		$("#errorsCity").show();
    	} else {
    		$("#errorsCity").hide();
    	}
    }
    
    function verifyState() {
    	var re = new RegExp("^[a-zA-Z]{2}$");
    	
    	if($("#state").val() == "") {
    		isValidAll = false;
    		$("#errorsState").text("This field should not be empty");
    		$("#errorsState").show();
    	} else if(!re.test($("#state").val())) {
    		isValidAll = false;
    		$("#errorsState").text("Should enter 2 characters");
    		$("#errorsState").show();
    	} else {
    		$("#errorsState").hide();
    	}
    }
    
    function verifyZipCode() {
    	var re = new RegExp("^[0-9]{5}$");
    	
    	if($("#zipCode").val() == "") {
    		isValidAll = false;
    		$("#errorsZipCode").text("This field should not be empty");
    		$("#errorsZipCode").show();
    	} else if(!re.test($("#zipCode").val())) {
    		isValidAll = false;
    		$("#errorsZipCode").text("Should enter 5 numbers");
    		$("#errorsZipCode").show();
    	} else {
    		$("#errorsZipCode").hide();
    	}
    }
    
    
});