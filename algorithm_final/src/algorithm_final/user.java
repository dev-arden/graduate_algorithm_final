package algorithm_final;

import java.util.*;

public class user extends line {
	public String userStationName;
	public Station userStation;
	public ArrayList<Station> buffer;
	public ArrayList<Integer> index; // buffer 늘어날때마다의 index 담기위한 칸 1칸 움직일 때 버퍼에 3개 담겼으면 3 늘어나고 이런 식
	public boolean flag;
	
	public user() {
		this.userStationName = null;
		this.flag = true;
		this.buffer = new ArrayList<Station>();
		this.index = new ArrayList<Integer>();
		this.index.add(0);

	}

	public void userpick() {
		System.out.println("\n지하철 고르쇼");
	
		System.out.println(this.userStationName + "역을 선택하셨습니다.");
		this.buffer.addAll(this.findStationInAll(AllLine, userStationName));
	}

	public void bufferSearch() {
		
		boolean flag = true;
		int line = 0;
		String name;
		Station prev = new Station();
		Station next = new Station();

		this.index.add(this.buffer.size()); // 시작 전 미리 버퍼의 사이즈를 index에 담는다.

		for (int i = this.index.get(this.index.size() - 2); i < this.index.get(this.index.size() - 1); i++) {

			line = this.findLine(this.buffer.get(i).code);
			name = this.buffer.get(i).name;

			prev = this.prevStation(AllLine.get(line), name);
			next = this.nextStation(AllLine.get(line), name);
			
			if (prev != null) { // null이 아니라는 전제 하에
				for (int k = 0; k < this.index.get(this.index.size() - 1); k++) {
					if (prev.name.equals(this.buffer.get(k).name))
						flag = false;
				}
				if (flag == true) // 이름 같은거 없다는것
					this.buffer.addAll(this.findStationInAll(AllLine, prev.name));
			}
			
			flag = true;
			
			if (next != null) { // null이 아니라는 전제 하에
				for (int k = 0; k < this.index.get(this.index.size() - 1); k++) {
					if (next.name.equals(this.buffer.get(k).name))
						flag = false;
				}
				if (flag == true) // 이름 같은거 없다는것
					this.buffer.addAll(this.findStationInAll(AllLine, next.name));
			}

		}
	} // buffer 내에 있는 사이즈 만큼

	public void bufferShow() {
		for (int i = 0; i < this.buffer.size(); i++) {
			System.out.print(this.buffer.get(i).code + "," + this.buffer.get(i).name + "\t");
		}
		System.out.println();
	}
}