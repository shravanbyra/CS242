<?php	
	include "server.php";
	include "partials/queryDiv.php";
	
	$wpnType = '';
	
	if($_SERVER["REQUEST_METHOD"] == "POST") {
		$wpnType = $_POST['wpn-type'];
	}
	
	$userQuery = 'SELECT DISTINCT skin FROM weapons WHERE ';
		
	if ($wpnType != '') {
		$userQuery = $userQuery . 'type="' . $wpnType . '"';
	}
		
	if ($userQuery != '') {
		$results = $db->query($userQuery);
	}
?>