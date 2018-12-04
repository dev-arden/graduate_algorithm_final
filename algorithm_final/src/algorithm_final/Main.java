package algorithm_final;

import java.util.*;

public class Main {
	public static void main(String[] args) {
		line subway = new line();
		int userCount = 0;

		System.out.println("사용자의 수는 몇명입니까 ? ");

		Scanner sc = new Scanner(System.in);
		userCount = sc.nextInt();
		user[] user = new user[userCount];
		ArrayList<String> commonset = new ArrayList<String>();

		for (int i = 0; i < userCount; i++) {
			user[i] = new user();
		}

		subway.setLine();
		subway.showLine();

		for (int i = 0; i < userCount; i++) {
			user[i].userpick();
		}

		for (int i = 0; i < userCount; i++) {
			user[i].bufferSearch();
		}

		for (int i = 0; i < userCount; i++) {
			user[i].bufferShow();
		}

		commonset = makeCommon(user);
		

		while (commonset.size() < 3) {
			System.out.println("공통 역의 갯수가 부족합니다. 재탐색합니다.");
			for (int i = 0; i < userCount; i++) {
				user[i].bufferSearch();
			} // 버퍼 늘림
			for (int i = 0; i < userCount; i++) {
				user[i].bufferShow();
			} // 버퍼 보여줌
			commonset = makeCommon(user); // 공통되는부분 찾는다.
			System.out.println(commonset.size());
			for (int k = 0; k < commonset.size(); k++) {
				System.out.print(commonset.get(k)+"\t");
			}
			System.out.println();
		}

		
		System.out.println("탐색이 완료되었습니다.");
		for (int i = 0; i < commonset.size(); i++) {
			System.out.print(commonset.get(i)+ "\t");
		}

	}

	public static ArrayList<String> makeCommon(user[] user) {
		ArrayList<String> commonset = new ArrayList<String>();
		int size = user.length;
		boolean flag = true;
		boolean tempFlag = true;
		String name0;
		
		user[0].flag = true;
		
		for(int i = 0; i < user[0].buffer.size() ; i++)
		{	
			name0 = user[0].buffer.get(i).name;
			for(int member = 1; member < size ; member++)
			{
				tempFlag = false;
				for(int j = 0; j < user[member].buffer.size();j++)
					tempFlag = tempFlag || (user[member].buffer.get(j).name.equals(name0));
				user[member].flag = tempFlag;
			}
			
			flag = true;
			
			for(int member = 1; member < size; member++)
			{
				flag = flag && (user[member].flag);
			}
			
			if(flag == true)
			{
				if(!commonset.contains(name0))
					commonset.add(name0);
			}
			
		}
		return commonset;
	}
}