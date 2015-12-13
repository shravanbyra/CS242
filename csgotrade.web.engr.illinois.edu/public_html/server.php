<?php
	$servername = "engr-cpanel-mysql.engr.illinois.edu";
	$username = "csgotrad_ruopp2";
	$password = "MR2016mr";
	$dbname = "csgotrad_db";
	
	$db = new mysqli($servername, $username, $password, $dbname);
	
	// Check connection
	if ($db->connect_error) {
    		die("Connection failed: " . $db->connect_error);
	} 
	
	$currentUser = '';
?>