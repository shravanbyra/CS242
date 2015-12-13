<?php
	include "server.php";

	if($_SERVER["REQUEST_METHOD"] == "POST") {
		$type = $_POST['type'];
		$skin = $_POST['skin'];
		$wear = $_POST['wear'];
		$numKeys = $_POST['numKeys'];
	}
	
	$aQuery = 'SELECT weapon_id FROM weapons WHERE type="' . $type . '" AND skin="' . $skin . ' AND wear="' . $wear . '"';
	$weaponId = $db->query($aQuery);
	
	
	$insert = 'INSERT INTO offers (num_keys, weapon_id) VALUES (' . $numKeys . ', ' . $weaponId . ')';
	
	$db->query($insert);
	
	header('Location: /');
	die();
?>