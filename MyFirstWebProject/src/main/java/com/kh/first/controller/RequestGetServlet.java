package com.kh.first.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/get.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 *	톰캣 실행 -> web.xml 파싱 
		 *	init() => service() => doGet() / doPost() => destroy()
		 *	서블릿 생명주기 / 싱글톤 패턴으로 객체 하나만을 사용 / 톰캣이 멀티스레드 처리(스레드풀) 
		 */
//		System.out.println("Really? 진짜임?");
		/*
		 * Dynamic WebProject 진행 시 Servlet을 Controller로 사용
		 * 
		 * 1. 데이터 가공
		 * 2. 요청처리(Service 호출)
		 * 3. 결과값 반환(응답화면지정)
		 * 
		 * View에서 Get방식으로 요청 시 doGet()이 호출함
		 */
//		System.out.println("GET방식으로 호출됨!");
		/*
		 * 인자값으로 두 개 넘어옴
		 * 
		 * 첫 번째 매개변수 HttpServletRequest 타입에는 요청 시 전달된 내용들이 담김
		 * => 요청 전송 방식, 요청 URL, 요청한 사용자의 정보, 사용자가 input요소에 입력한 값 등
		 * 
		 * 두 번째 매개변수 HttpServletResponse 타입은 요청 처리 후 응답할 때 사용하는 객체
		 * 
		 * 요청 처리 스텝
		 * 
		 * 1. 우선 요청을 처리하기 위해서 요청 시 전달된 값(사용자가 입력한 값)들을 뽑는다.
		 * => key-value세트로 담겨있음(name속성값=value속성값)
		 * => request의 Parameter라는 곳에서 전달값을 뽑아내야함
		 * 
		 *  2. 뽑아낸 값을 가공해서 요청 처리를 진행해야 함(Service -> DAO -> DB)
		 *  
		 *  3. 처리 결과에 따른 성공 / 실패 페이지 응답
		 */
		
		/*
		 * request의 parameter영역으로부터 전달된 데이터 값을 뽑아내는 방법
		 * 
		 * - request.getParameter("키값") : String(input요소에 적어놓은 name속성값)
		 * => 반환형이 String이기 때문에 다른 자료형으로 사용해야 한다면 Parsing해야함
		 * 
		 * - request.getParameterValues("키값") : String[]
		 * => 하나의 key값으로 여러 개의 value들을 받아야 하는 경우 
		 */
		
		// 1단계. 값 뽑기
		String name = request.getParameter("name");
		System.out.println(name);
		// 경우의수: "셀 수 없음"
		
		// 1. 정상적인 값 입력
		// 2. 아무것도 입력X : ""(빈 문자열 반환)
		// 3. key값을 지워서 입력 : null
		
		String gender = request.getParameter("gender");
		System.out.println(gender);
		// 1. 정상적인 값: "남" / "여" / "선택안함"
		// 2. 아무것도 체크 x : null
		
		//int age = request.getParameter("age") 반환형이 String이므로 int 변수에 x, parsing필요
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(age);
		// 1. 정상적인 값 입력 : "14"
		// 2. 아무것도 입력x : 빈 문자열이 입력 > int parsing이 불가능하므로 NumberFormatException발생
		
		String city = request.getParameter("city");
		System.out.println(city);
		// 1. 정상 값 : "서울" / "제주도"
		
		double height = Double.parseDouble(request.getParameter("height"));
		System.out.println(height);
		int realHeight = (int)height; //자료형이 달라 에러발생: 강제형변환(주의할것)
		
		
		
		String[] foods = request.getParameterValues("food");
		System.out.println(Arrays.toString(foods));
		// 1. 정상 값: [피자, 치킨]
		// 2. key값 제외 : null
		
		// 자주보는 상태코드
		// 404 : 파일 또는 요청을 받아주는 서블릿을 찾지 못했을 때 발생
		//		=> 대개 경로를 잘 못 적었거나 파일명에 오타가 났을 때
		// 500 : 내부 서버에서 오류가 났을 때(== 자바소스코드상의 오류 / 예외발생)
		
		// 2댠계. 데이터 가공
		// Person person = new Person(name, gender, age, city, height, foods);
		// 만들었다고 가정
		
		// 3단계. 요청 처리(DB와의 상호작용 == JDBC/MyBatis)
		// 보통의 흐름: Controller에서 Service의 메서드를 호출하면서 값을 전달
		// -> DAO 호출 -> DB SQL문 실행 -> 정수형태의 결과값 반환
		
		// 
		// 4단계. 결과값 반환 or 응답화면 지정
		// 무조건 성공했다고 가정
		
		// 순수Servlet만 사용해서 응답데이터 넘기기
		// 사용자에게 HTML + CSS + JS응답
		
		/*
		 * 요청 처리에 성공했습니다.
		 * 
		 * XXX님은
		 * XX살이며, 
		 * XXX에 삽니다. 
		 * 키는 XXXcm이고
		 * 
		 * 		case 2. 남성입니다.
		 * 		case 3. 여성입니다.
		 * 
		 * 좋아하는 음식 case 1. 없습니다.
		 *			  case 2. 치킨머시기~ 떢복이~
		 * 
		 */
		
		// 1단계) 응답 데이터 형식 지정 -> 문서형태의 HTML / 인코딩 방식 UTF-8
		response.setContentType("text/html; charset=UTF-8");
		
		// 2단계) 출력 스트림 생성 
		// 스트림 InputStream / OutStream
		// 			Reader 	/ Writer
		PrintWriter pw = response.getWriter();
		
		// 3단계) 스트림 이용해서 HTML데이터 출력
        pw.println("<html>");
        pw.println("<head>");
        pw.println("<title>순수 서블릿으로 응답해보기</title>");
        pw.println("<style>");
        pw.println("#name{color: orange;}");
        pw.println("#age{color: orange;}");
        pw.println("#city{color: orange;}");
        pw.println("#height{color: orange;}");
        pw.println("#gender{color: orange;}");
        pw.println("</style>");
        pw.println("</head>");
        pw.println("<body>");

        pw.println("<h1>요청 처리에 성공했습니다.</h1>");
        pw.printf("<span id='name'>%s</span>님은<br>", name);
        pw.printf("<span id='age'>%d</span>살이며,<br>", age);
        pw.printf("<span id='city'>%s</span>에 삽니다.<br>", city);
        pw.printf("키는 <span id='height'>%.1f</span>cm이고<br><br>", height);

        pw.print("성별은 ");
        if (gender == null || "선택안함".equals(gender)) {
            pw.println("선택을 안했습니다.");
        } else if (gender.equals("남")) {
            pw.println("<span id='gender'>남성입니다.</span>");
        } else {
            pw.println("<span id='gender'>여성입니다.</span>");
        }

        if (foods == null) {
            pw.println("없습니다.");
        } else {
            pw.println("<ul>");
            for (int i = 0; i < foods.length; i++) {
                pw.printf("<li style='color: plum'>%s</li>", foods[i]);
            }
            pw.println("</ul>");
            pw.println("입니다.");
        }

        pw.println("<script>");
        pw.println("alert('축하해');");
        pw.println("</script>");

        pw.println("</body>");
        pw.println("</html>");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
