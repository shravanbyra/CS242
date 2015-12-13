<?php
    include 'assignment entry.php';
	include 'FilesEntry.php';

    /**
     * This class is responsible for the creation of
     */
	class Parser {
		private $parsedInfo;
		private $svn_log;
		private $svn_list;
		
		public function __construct() {
			$this->svn_log = simplexml_load_file("svn_log.xml");
            $this->svn_list = simplexml_load_file("svn_list.xml");
            $this->parsedInfo = array();
		}

        public function parse() {
            $svnLog = $this->svn_log;
            $svnList = $this->svn_list;
            $revision = array();
            $author = array();
            $date = array();
            $message = array();
            $files = array();

            $assignmentName = $this->parseAssignmentNames($svnList);

            foreach ($svnLog as $entry) {
                $revision[] = $entry['revision'];
                $author[] = $entry->author;
                $date[] = $entry->date;
                $tempPathArray = array();
                foreach($entry->paths as $path) {
                    $tempPathArray[] = $path;
                }
                $files[] = $tempPathArray;
                $message = $entry->msg;
            }

            // Write Parsed Data into our object for storage
            for($i = 0; $i < count($assignmentName); $i++) {
                $this->parsedInfo[] = new AssignmentEntry();
                $this->parsedInfo[$i]->setName($assignmentName[$i]);

                // Go through the Log info and sort and fill in the FilesEntry Object
                $tempFileEntry = null;
                for($z = 0; $z < count($revision); $z++) {
                    if(strpos($files[$z][0], $assignmentName[$i]) !== false) {
                        $tempFileEntry = $this->createFileEntry($author[$z], $message[$z],
                            $date[$z], $files[$z]);
                        $this->parsedInfo[$i]->addFileEntry($tempFileEntry, $revision[$z]);
                    }
                }

            }

        }

        /**
         * This function handles creating a File Entry Object
         *
         * @param $author - author for the commit
         * @param $message - the commit message
         * @param $date - the date for the commit
         * @param $filePaths - the array of file paths
         * @return a File Entry object with all the information passed to it.
         */
        public function createFileEntry($author, $message, $date, $filePaths) {
            $entry = new FilesEntry();
            $entry->setAuthor($author);
            $entry->setComment($message);
            $entry->setDate($date);
            $entry->setFilePaths($filePaths);

            $tempFilesArray = $this->parseFileNames($filePaths);
            $entry->setFiles($tempFilesArray);

            return $entry;
        }

        /**
         * This function goes through a list of file paths and extracts the
         * file names.
         *
         * @param $filePaths - an array of paths for files to go through
         * @return an array of file names without the whole path
         */
        public function parseFileNames($filePaths) {
            $files = array();

            foreach($filePaths as $filePath) {
                $files[] = strrchr($filePath, "/");
            }

            return $files;
        }

        /**
         * This function parses the names for Assignment Entries from
         * the svn list object.
         *
         * @param $svnList - a svn list object parsed from the xml
         * @return array of names for the Assignments
         */
        public function parseAssignmentNames($svnList) {
            $assignmentName = array();
            $names = array();
            foreach (($svnList->list[0]) as $assignment) {
                $names[] = (string)$assignment->name;
            }


            $target = $names[0];
            foreach ($names as $name) {
                if(strpos((string)$name, $target) === false) {
                    $assignmentName[] = $target;
                    $target = (string)$name;
                }
            }

            $assignmentName[] = $target;
            return $assignmentName;
        }


        /**
         * @return returns the array of data of parsed information
         */
        public function getParsedInfo() {
            return $this->parsedInfo;
        }
	}
?>