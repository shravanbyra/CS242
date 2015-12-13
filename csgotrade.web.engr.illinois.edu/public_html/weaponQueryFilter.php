<?php
	$filterOptions = '<div id="weaponFilterOptions"><form id="" class="" name="sub-offer-form" action="/subOffer.php" method="post" onsubmit="return validateOffer()">';
	
	$types = $db->query('SELECT DISTINCT type FROM weapons ORDER BY type');
	$filterOptions = $filterOptions . 'Type: <select name="type">';
	while ($row = $types->fetch_assoc()) {
		$filterOptions = $filterOptions . '<option value="' . $row['type'] . '">' . $row['type'] . '</option>';
	}
	$filterOptions = $filterOptions . '</select>';
	
	$skins = $db->query('SELECT DISTINCT skin FROM weapons ORDER BY skin');
	$filterOptions = $filterOptions . 'Skin: <select name="skin">';
	while ($row = $skins->fetch_assoc()) {
		$filterOptions = $filterOptions . '<option value="' . $row['skin'] . '">' . $row['skin'] . '</option>';
	}
	$filterOptions = $filterOptions . '</select>';
	
	$wears = $db->query('SELECT DISTINCT wear FROM weapons ORDER BY wear');
	$filterOptions = $filterOptions . 'Wear: <select name="wear">';
	while ($row = $wears->fetch_assoc()) {
		$filterOptions = $filterOptions . '<option value="' . $row['wear'] . '">' . $row['wear'] . '</option>';
	}
	$filterOptions = $filterOptions . '</select>';
	
	$filterOptions = $filterOptions . '<input id="newOfferNumKeys" type="text" name="numKeys" placeholder="# of keys">';
	
	$filterOptions = $filterOptions . '<input type="submit" value="Add Offer">';
			
	$filterOPtions = $filterOptions . '</form></div>';
?>