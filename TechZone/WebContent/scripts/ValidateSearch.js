/**
 * 
 */

function validateSearch(){
	var val=document.getElementById("s").value;
	if (val.length>0&&val.length<255) return true;
	return false;
}