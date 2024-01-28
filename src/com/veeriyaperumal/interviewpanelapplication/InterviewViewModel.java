package com.veeriyaperumal.interviewpanelapplication;

import java.util.Queue;

import com.veeriyaperumal.interviewpanelapplication.dto.Candidate;
import com.veeriyaperumal.interviewpanelapplication.repository.Repository;

public class InterviewViewModel implements Runnable {

	private Thread interviewThread;
	private boolean isInterviewStarted = false;
	final private int INTERVAL_TIME = 10000;// in milli second
	private InterviewView view;
	private Repository repository;

	public InterviewViewModel(InterviewView interviewView) {
		view = interviewView;
		repository = Repository.getInstance();
	}

	public void addCandidate(Candidate candidate) {
		repository.addCandidate(candidate);
		if (!repository.getCandidatesQueue().isEmpty() && isInterviewStarted()) {
			interviewThread = new Thread(this);
			interviewThread.start();
		}
	}

	public Queue<Candidate> getCandidatesQueue() {
		return repository.getCandidatesQueue();
	}

	public Candidate getCurrentInterviewingCandidate() {
		return repository.getCandidatesQueue().peek();
	}

	void startInterview() {
		if (isInterviewStarted) {
			view.printUserMessage("Interview already started.");
			return;
		}
		setInterviewStarted(true);
		interviewThread = new Thread(this);
		interviewThread.start();
		view.printInterviewStatusMessage("Interview started");
	}

	void endInterview() {
		if (!isInterviewStarted) {
			view.printUserMessage("Interview not started.");
			return;
		}
		setInterviewStarted(false);
		view.printInterviewStatusMessage("Interview ended.");
	}

	@Override
	public void run() {
		try {
			Thread.sleep(INTERVAL_TIME);
			finishInterview();
			if (!repository.getCandidatesQueue().isEmpty()) {
				interviewThread = new Thread(this);
				interviewThread.start();
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void finishInterview() {
		if (!repository.getCandidatesQueue().isEmpty() && isInterviewStarted()) {
			view.printUserMessage("Interview finished for " + repository.getCandidatesQueue().peek().getName());
			repository.getCandidatesQueue().poll();
		}
	}

	public boolean isInterviewStarted() {
		return isInterviewStarted;
	}

	public void setInterviewStarted(boolean isInterviewStarted) {
		this.isInterviewStarted = isInterviewStarted;
	}
}