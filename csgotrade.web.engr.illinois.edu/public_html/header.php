<?php
	include "navigationLinks.php";
	
	$headerRaw = '<div id="header">';
	
	$path = '<span id="path">' . $path . '</span>';
	$pageTitle = '<h1 id="page-title" class="header-item"><a href="/">CS:GO Trading Marketplace</a></h1>' . $path;
		
	$headerRaw = $headerRaw . $pageTitle;
		
	
	if ($currentUser != '') {
		# user is logged in, display their profile
		$userDisplay = '<div id="current-user" class="header-item user-display">Hello, ' . $currentUser->fetch_assoc()['username'] . '</div>';
	
	} else {
		# user is not logged in, so display sign up and login buttons
		$userDisplay = '<div id="sign-up-and-login" class="header-item user-display"><span id="sign-up-button"><a href="/register.php">Sign up</a></span> or <a href="/login.php">login</a></div>';
	}
	
		
	$headerRaw = $headerRaw . $userDisplay;
	
	$navPanel = '<div id="nav-panel"><ul id="nav-panel-list">';
	foreach ($links as $link) {
		$navPanel = $navPanel . '<li>' . $link . '</li><br/>';
	}			
	$navPanel = $navPanel . '</ul></div>';
	
	$headerRaw = $headerRaw . $navPanel;
	
	$headerRaw = $headerRaw . '</div>';		
	$header = $headerRaw;
	
?>