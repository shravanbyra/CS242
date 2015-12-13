<?php	
	include "server.php";
	include "partials/queryDiv.php";
	
	$wpnType = '';
	$wpnSkin = '';
	$wpnWear = '';
	
	if($_SERVER["REQUEST_METHOD"] == "POST") {
		$wpnType = $_POST['wpn-type'];
		$wpnSkin = $_POST['wpn-skin'];
		$wpnWear = $_POST['wpn-wear'];
	}
	
	$userQuery = 'SELECT wear FROM weapons WHERE ';
		
	if ($wpnType != '') {
		$userQuery = $userQuery . 'type="' . $wpnType . '" AND ';
	}
	
	if ($wpnSkin != '') {
		$userQuery = $userQuery . 'skin="' . $wpnSkin . '"';
	}
		
	if ($userQuery != '') {
		$results = $db->query($userQuery);
	}
?>