package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import vo.GoodVO;

public class GoodDao {	
	public GoodDao(){}
	
	//전체 데이터를 읽어오는 메소드
	//파라미터를 만들 때는 insert나 update는 VO
	//delete는 primary key
	//select는 where절에 대입되어야 하는 데이터 
	//select * from goods
	//절대로 선언을 할때 ArrayList or LinkedList로 선언하지 안습니다.바뀔여지가 있기때문
	public List<GoodVO> getGood(){	
		//List를 리턴할 때는 객체를 생성하고 리턴하도록 작성
		List<GoodVO> list = new ArrayList<GoodVO>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			//오라클 드라이버 로드
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection("jdbc:oracle:thin:@192.168.10.101:1521:xe",
					"user18", "user18");
			pstmt = con.prepareStatement("select * from goods");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				GoodVO vo = new GoodVO();
				vo.setCode(rs.getInt("code"));
				vo.setName(rs.getString("name"));
				vo.setPrice(rs.getInt("price"));
				vo.setDescription(rs.getString("description"));
				list.add(vo);
		}
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(con != null)con.close();
				if(pstmt != null)pstmt.close();
				if(rs != null)rs.close();
			}catch(Exception e) {}
		}
		return list;
		
	}
}
