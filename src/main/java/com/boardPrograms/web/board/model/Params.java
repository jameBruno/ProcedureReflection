
package com.boardPrograms.web.board.model;

import java.util.HashMap;
import java.util.List;

public class Params {
	
	@DBtable(columnName = "CAMP_ID")
	private String CAMP_ID;
	@DBtable(columnName = "CAMP_STAT_CD")
	private String CAMP_STAT_CD;
	@DBtable(columnName = "SCENARIO_NAME")
	private String SCENARIO_NAME;
	@DBtable(columnName = "CAMP_NAME")
	private String CAMP_NAME;
	@DBtable(columnName = "CAMP_COUNT")
	private int CAMP_COUNT;
	@DBtable(columnName = "GRP_VDN")
	private String GRP_VDN;
	@DBtable(columnName = "GRP_NAME")
	private String GRP_NAME;
	@DBtable(columnName = "GRP_NAME")
	private String TR_NAME;
	@DBtable(columnName = "CALLLIST_NAME")
	private String CALLLIST_NAME;
	private List<Params> result;	
	
	/*
	 * public Params(String campID, String sWorkSect, String sCallListName, String
	 * sPreNext, int iSequence, String sFieldName, String sAccount, String sText,
	 * String sFilterSect) { //super(); this.CampID = campID; this.sWorkSect =
	 * sWorkSect; this.sCallListName = sCallListName; this.sPreNext = sPreNext;
	 * this.iSequence = iSequence; this.sFieldName = sFieldName; this.sAccount =
	 * sAccount; this.sText = sText; this.sFilterSect = sFilterSect; }
	 */
	
	public Params() {
		// TODO Auto-generated constructor stub
	}
	
	public String getCAMP_ID() {
		return CAMP_ID;
	}
	public void setCAMP_ID(String campID) {
		CAMP_ID = campID;
	}
	public String getCAMP_STAT_CD() {
		return CAMP_STAT_CD;
	}
	public void setCAMP_STAT_CD(String CAMP_STAT_CD) {
		this.CAMP_STAT_CD = CAMP_STAT_CD;
	}
	public String getSCENARIO_NAME() {
		return SCENARIO_NAME;
	}
	public void setSCENARIO_NAME(String SCENARIO_NAME) {
		this.SCENARIO_NAME = SCENARIO_NAME;
	}
	public String getCAMP_NAME() {
		return CAMP_NAME;
	}
	public void setCAMP_NAME(String CAMP_NAME) {
		this.CAMP_NAME = CAMP_NAME;
	}
	public int getCAMP_COUNT() {
		return CAMP_COUNT;
	}
	public void setCAMP_COUNT(int CAMP_COUNT) {
		this.CAMP_COUNT = CAMP_COUNT;
	}
	public String getGRP_VDN() {
		return GRP_VDN;
	}
	public void setGRP_VDN(String GRP_VDN) {
		this.GRP_VDN = GRP_VDN;
	}
	public String getGRP_NAME() {
		return GRP_NAME;
	}
	public void setGRP_NAME(String GRP_NAME) {
		this.GRP_NAME = GRP_NAME;
	}
	public String getTR_NAME() {
		return TR_NAME;
	}
	public void setTR_NAME(String TR_NAME) {
		this.TR_NAME = TR_NAME;
	}
	public String getCALLLIST_NAME() {
		return CALLLIST_NAME;
	}
	public void setCALLLIST_NAME(String CALLLIST_NAME) {
		this.CALLLIST_NAME = CALLLIST_NAME;
	}
	public List<Params> getResult() {
		return result;
	}
	public void setResult(List<Params> result) {
		this.result = result;
	}
	
	
}
