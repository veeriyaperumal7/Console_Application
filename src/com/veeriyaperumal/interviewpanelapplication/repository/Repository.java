package com.veeriyaperumal.interviewpanelapplication.repository;

import java.util.LinkedList;
import java.util.Queue;

import com.veeriyaperumal.interviewpanelapplication.dto.Candidate;

public class Repository {
	private Queue<Candidate> candidatesQueue;
	private static Repository repository;

	private Repository() {
		candidatesQueue = new LinkedList<>();
	}

	public static Repository getInstance() {
		if (repository == null) {
			repository = new Repository();
		}
		return repository;
	}

	public Queue<Candidate> getCandidatesQueue() {
		return candidatesQueue;
	}

	public void addCandidate(Candidate candidate) {
		candidatesQueue.add(candidate);
	}

}
