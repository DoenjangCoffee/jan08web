package com.poseidon.team;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamC {
	List<String> members = new ArrayList<String>();
	public List<List<String>> team = new ArrayList<List<String>>();

	public TeamC() {
		members.add("신동일");
		members.add("한솔범");
		members.add("백건하");
		members.add("박선우");
		members.add("채영선");
		members.add("노재희");
		members.add("박대종");
		members.add("이민우");
		members.add("나우석");
		members.add("신유진");
		members.add("정효진");
		members.add("이지은");
		members.add("손현석");
		members.add("신진수");
		members.add("이남규");
		members.add("이문희");
		members.add("정은숙");
		members.add("오초록");
		members.add("박시호");
		members.add("배현배");
		members.add("김지훈");
		members.add("이진선");
		members.add("이수현");
		members.add("강기병");
		members.add("정진수");
		members.add("오상민");
	}

	public void setTeam(int m) {
		Collections.shuffle(members);
		for (int i = 0; i < m; i++) {
			List<String> e = new ArrayList<String>();
			team.add(e);
		}
	}

	public void choose(int m) {
		setTeam(m);
//		/int member = members.size() / m;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < m; j++) {
				team.get(i).add(members.remove(0));
			}
			if(i == m - 1 && members.size() % m != 0) {
				team.get(i).add(members.remove(0));
			}
		}
	}
}
