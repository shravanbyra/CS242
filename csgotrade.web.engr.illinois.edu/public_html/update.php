<?php
	include "server.php";
	
	$path = ' > update';
	
	$wpnType = '';
	$wpnSkin = '';
	$wpnWear = '';
	$wpnCost = ''; 
	
	if($_SERVER["REQUEST_METHOD"] == "POST") {
		$wpnTypeOld = $_POST['wpn-type-old'];
		$wpnSkinOld = $_POST['wpn-skin-old'];
		$wpnWearOld = $_POST['wpn-wear-old'];
		$wpnCostOld = $_POST['wpn-cost-old'];
		
		$wpnTypeNew = $_POST['wpn-type-new'];
		$wpnSkinNew = $_POST['wpn-skin-new'];
		$wpnWearNew = $_POST['wpn-wear-new'];
		$wpnCostNew = $_POST['wpn-cost-new'];
	}
	
	$userQuery = 'UPDATE weapons SET type="' . $wpnTypeNew . '", skin="' . $wpnSkinNew . '", wear="' . $wpnWearNew . '", avg_price="' . $wpnCostNew . 
		'" WHERE type="' . $wpnTypeOld . '" AND skin="' . $wpnSkinOld . '" AND wear="' . $wpnWearOld . '" AND avg_price="' . $wpnCostOld . '"';
		
	if ($db->query($userQuery) === TRUE) {
		$result = '<p>Successfully updated [' . $wpnTypeOld . ', ' . $wpnSkinOld . ', ' . $wpnWearOld . ', ' . $wpnCostOld . ']</p>';
	} else {
		$result = '<p>Failed to update the data.<p/>';
	}
	

?>

<!DOCTYPE html>
<html>
<head>
	<?php
	include "head.php";
	include "header.php";
	include "partials/queryDiv.php";
	include "partials/userInputUpdate.php";
	include "partials/options.php";
	echo $head;
	?>
</head>
<body>
	<div id="container">
		<?php
		echo $header;
		echo $queryDiv;
		echo $result;
		
		$db->close();
		?>
	</div>
</body>
</html>