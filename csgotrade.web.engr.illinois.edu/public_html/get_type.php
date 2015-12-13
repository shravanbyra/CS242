<?php	
	include "server.php";
	include "partials/queryDiv.php";
	
	$userQuery = 'SELECT DISTINCT type FROM weapons
		
	if ($userQuery != '') {
		$results = $db->query($userQuery);
	}
?>