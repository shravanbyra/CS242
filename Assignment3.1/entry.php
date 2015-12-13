<head>
	<link rel="stylesheet" type="text/css" href="style.css" />
</head>
<html>
	<body>
		<?php
			class logEntry {
				public $paths;
				public $comment;
				public $date;
				public $author;
				public $revision;
				
				
				public function __construct(){
					$paths = array();
				}
			}
			
			function determineType($tag){
				if(strcmp($tag, "") == 0) {
					return "unknown";
				}
				elseif(strcmp($tag, "txt") == 0) {
					return "documentation";
				}
				elseif(strcmp($tag, "java") == 0 || strcmp($tag, "py") == 0) {
					return "code";
				}
				else {
					return "resource";
				}
			}
			
			$entryName = $_GET['name'];
			$revision = $_GET['revision'];
			
			$svnLog = simplexml_load_file("svn_log.xml");
			
			$paths = array();
			$resultLogs = array();			

			foreach($svnLog as $entry) {
				$tempPath = (string)$entry->paths[0]->path[0];
				if(strpos($tempPath, $entryName)) {
					$tempLog = new logEntry();
					$tempPathList = array();

					foreach($entry->paths[0] as $path) {
						 $tempPathList[] = (string)$path;
					}
					
					$tempLog->paths = $tempPathList;
					$tempLog->comment = (string)$entry->msg;
					$tempLog->date = (string)$entry->date;
					$tempLog->author = (string)$entry->author;
					$tempLog->revision = (string)$entry['revision'];
					
					$resultLogs[] = $tempLog;
				}
			}
			
			echo "<h1>" . $entryName . "</h1>";
		?>
		</br>
		<?php
			foreach($resultLogs as $entry) {
				echo "<h3>Revision " . $entry->revision . "</h3>";
				echo "<p><b>Author: </b>" . $entry->author . "</br>";
				echo "<b>Date: </b>" . $entry->date . "</br>";
				echo "<b>Message: </b>" . $entry->comment . "</p>"; 
				
				$tempPaths = $entry->paths;
				echo "<table>";
				echo "<tr>";
				echo "<td><b>File Name</b></td>";
				echo "<td><b>Path</b></td>";
				echo "<td><b>Code</b></td>";
				echo "<td><b>File Type</b></td>";
				echo "</tr>";
				
				$webPrefix = "https://subversion.ews.illinois.edu/svn/fa12-cs242";
				foreach($tempPaths as $path) {
					$fileName = substr(strrchr($path, "/"), 1);
					$fileType = substr(strrchr($fileName, "."), 1);
					echo "<tr>";
					echo "<td>" . $fileName . "</td>";
					echo "<td>" . $path . "</td>";
					echo "<td><a href=" . $webPrefix . $path . ">Click to See Code</a>";
					echo "<td>" . determineType($fileType). "</td>";
					echo "</tr>";
				}
				
				echo "</table>";
				echo "</br>";
			}
		?>
	</body>
</html>