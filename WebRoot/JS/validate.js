document.charset="utf-8";

function validate_required(field,alerttxt)
{
with (field)
  {
  if (value==null||value=="")
    {alert(alerttxt);return false;}
  else {return true;}
  }
}
function validate_checkpassword(field1,field2,alerttxt)
{
	if(field1.value!=field2.value)
	{
		alert(alerttxt);
	    return false;	
	}
	else
	{
		
		return true;
	}
}

function validate_form_login(thisform)
{
with (thisform)
  {
  if (validate_required(username,"username must be filled out")==false)
    {username.focus();return false;}
  if (validate_required(password,"password  must be filled out")==false)
    {password.focus();return false;}
  }
}
function validate_article(thisform)
{
with (thisform)
  {
  if (validate_required(title,"title must be filled out")==false)
    {title.focus();return false;}
  }
}

function validate_userreg(thisform)
{
with (thisform)
  {
	if (validate_required(username,"username must be filled out")==false)
    {username.focus();return false;}
	if (validate_required(password,"password must be filled out")==false)
    {password.focus();return false;}
	if (validate_required(password2,"password must be filled out")==false)
    {password2.focus();return false;}
	if(validate_checkpassword(password,password2,"two password don't equal")==false)
	{
		password.focus();return false;
	}
	if (validate_required(email,"email must be filled out")==false)
    {email.focus();return false;}
 
  }
}