<?php

	/**
	 * This class is designed to hold the relevant information for the
	 *
	 */
	class FilesEntry
	{
	    private $files;
	    private $filePaths;
	    private $date;
	    private $author;
	    private $comment;
	
	    public function __construct() {
	        $this->author = "";
	        $this->filePaths = array();
	        $this->date = "";
	        $this->author = "";
	        $this->comment = "";
	        $this->files = array();
	    }
	
	    /**
	     * @param $author - the author of the commit
	     */
	    public function setAuthor($author)
	    {
	        $this->author = $author;
	    }
	
	    /**
	     * @return the author for this commit
	     */
	    public function getAuthor()
	    {
	        return $this->author;
	    }
	
	    /**
	     * @param $comment - the comment for this commit
	     */
	    public function setComment($comment)
	    {
	        $this->comment = $comment;
	    }
	
	    /**
	     * @return the comment associated to the commit
	     */
	    public function getComment()
	    {
	        return $this->comment;
	    }
	
	
	    /**
	     * Converts the given date to MonthName Day, Year at Hour:Minutes AM/PM
	     * @param $date - the date information from SVN
	     */
	    public function setDate($date)
	    {
	        // replacing the T with a space so it can be parsed
	        $date[strpos($date, "T")] = " ";
	        $dateInfo = date_parse($date);
	
	        $resultDate = $this->determineMonth($dateInfo['month']) . " " . $dateInfo['day'] . ", " .
	            $dateInfo['year'] . " at " . $this->formatTime($dateInfo["hour"], $dateInfo["minute"]);
	
	        $this->date = $resultDate;
	    }
	
	    /**
	     * Formats the input to give an output like 12hr:min AM/PM
	     * @param $hour - string 24hr input for the time
	     * @param $min - string for the minutes
	     * @return the formatted version the time
	     */
	    private function formatTime($hour, $min){
	        $isAM = intval($hour) < 12;
	        $realHour = (intval($hour) % 12) . "";
	
	        if($isAM) {
	            return $realHour . ":" . $min . " AM";
	        }
	        else {
	            return $realHour . ":" . $min . " PM";
	        }
	    }
	
	    /**
	     * @param $number - the string of the month number
	     * @return the string for the Month
	     */
	    private function determineMonth($number) {
	        if($number === "01"){
	            return "January";
	        }
	        elseif($number === "02") {
	            return "February";
	        }
	        elseif($number === "03") {
	            return "March";
	        }
	        elseif($number === "04") {
	            return "April";
	        }
	        elseif($number === "05") {
	            return "May";
	        }
	        elseif($number === "06") {
	            return "June";
	        }
	        elseif($number === "07") {
	            return "July";
	        }
	        elseif($number === "08") {
	            return "August";
	        }
	        elseif($number === "09") {
	            return "September";
	        }
	        elseif($number === "10") {
	            return "October";
	        }
	        elseif($number === "11") {
	            return "November";
	        }
	        elseif($number === "12") {
	            return "December";
	        }
	        else {
	            return "Unknown";
	        }
	    }
	
	    /**
	     * @return the string for the date
	     */
	    public function getDate()
	    {
	        return $this->date;
	    }
	
	    /**
	     * @param $filePaths - an array of paths correlating to the files
	     */
	    public function setFilePaths($filePaths)
	    {
	        $this->filePaths = $filePaths;
	    }
	
	    /**
	     * @param $filePath -  the file path to add to the array
	     */
	    public function addFilePath($filePath) {
	        $this->filePaths[] = $filePath;
	    }
	    /**
	     * @return the array of file paths for the various files
	     */
	    public function getFilePaths()
	    {
	        return $this->filePaths;
	    }
	
	    /**
	     * @param sets the array of files for the revision
	     */
	    public function setFiles($files)
	    {
	        $this->files = $files;
	    }
	
	    /**
	     * @param $file - the file to add to entry's list
	     */
	    public function addFile($file) {
	        $this->files[] = $file;
	    }
	
	    /**
	     * @return the array of files associated with the entry
	     */
	    public function getFiles()
	    {
	        return $this->files;
	    }
	}
?>