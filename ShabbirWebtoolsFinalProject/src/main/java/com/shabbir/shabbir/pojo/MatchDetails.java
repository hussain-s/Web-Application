package com.shabbir.shabbir.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="matchdetails")
public class MatchDetails {

	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "matchId", unique=true, nullable = false)
	private Integer matchId;
	@Column(name = "matchDate", nullable = false, length = 45)
	private String matchDate;
	@Column(name = "venue", nullable = false, length = 45)
	private String venue;
	
	public MatchDetails() {
	
	}

	public MatchDetails(String matchDate, String venue) {
	
		this.matchDate = matchDate;
		this.venue = venue;
	}

	public Integer getMatchId() {
		return matchId;
	}

	public void setMatchId(Integer matchId) {
		this.matchId = matchId;
	}

	public String getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = matchDate;
	}

	public String getVenue() {
		return venue;
	}

	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	
	
	
	
}
