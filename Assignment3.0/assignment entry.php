<?php

	/**
	 * This is the class file for Assignments to hold all the relevant
	 * Assignment information.
     *
     * @author Joshua Harris
	 */

	class AssignmentEntry
	{
	    private $name;
	    private $fileEntries;
	
	    public function __construct() {
	        $this->name = "";
	        $this->fileEntries = array();
	    }
	
	    /**
	     * @param $name - The new name for the assignment
	     */
	    public function setName($name) {
	        $this->name = $name;
	    }
	
	    /**
	     * @return the name of the assignment
	     */
	    public function getName() {
	        return $this->name;
	    }
	
	    /**
	     * @param $entry - The file entry to add to the list
	     * @param $revision - The revision associated with the file entry
	     */
	    public function addFileEntry($entry, $revision){
	        $this->fileEntries[$revision] = $entry;
	    }
	
	    /**
	     * @return the array of file entries for the assignment
	     */
	    public function getFileEntries() {
	        return $this->fileEntries;
	    }
	
	    /**
	     * @return the array of revisions associated with the list of file entries
	     */
	    public function getRevisions() {
	        return array_keys($this->fileEntries);
	    }
	}
?>