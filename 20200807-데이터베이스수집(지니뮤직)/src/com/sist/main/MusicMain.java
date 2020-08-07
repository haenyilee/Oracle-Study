package com.sist.main;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.MusicDAO;
import com.sist.dao.MusicVO;

public class MusicMain {

	public static void main(String[] args) {
		MusicDAO dao = new MusicDAO();
		
		// �󼼰��� : https://github.com/haenyilee/JAVA_Basic/wiki/Java_%EB%8D%B0%EC%9D%B4%ED%84%B0%EB%B2%A0%EC%9D%B4%EC%8A%A4_%EC%A7%80%EB%8B%88%EB%AE%A4%EC%A7%81%EB%8D%B0%EC%9D%B4%ED%84%B0%EC%88%98%EC%A7%91
		try {
			int k=1;
			for(int i=1;i<=4;i++)
			{
				try
				{
				Document doc = Jsoup.connect("https://www.genie.co.kr/chart/top200?ditc=D&ymd=20200807&hh=09&rtm=Y&pg="+i).get();
				// �ҽ� �� ������ Ȯ�ο� : System.out.println(doc);
				Elements title=doc.select("td.info a.title");
        		Elements singer=doc.select("td.info a.artist");
        		Elements album=doc.select("td.info a.albumtitle");
        		Elements poster=doc.select("a.cover img");  // <>�ȿ� �ִ� ����
        		Elements temp=doc.select("span.rank");
        		
        		for(int j=0;j<title.size();j++)
        		{
        			// html ���� �� �������� Ȯ�ο�
        			System.out.println("����:"+k);
        			System.out.println("�뷡��:"+title.get(j).text());
        			System.out.println("������:"+singer.get(j).text());
        			System.out.println("�ٹ���:"+album.get(j).text());
        			System.out.println("������:"+poster.get(j).attr("src"));
        			//System.out.println("���µ���:"+temp.get(j).text());
        			
        			// ���µ����� �پ ��µǴϱ� �� ���� �߶��ֱ�
        			String str=temp.get(j).text();
        			String idcrement="";
        			String state="";
        			if(str.equals("����"))
        			{
        				idcrement="0";
        				state="����";
        			}
        			else if(str.equals("new"))
        			{
        				idcrement="0";
        				state="new";
        			}
        			else
        			{
        				// ���ڻ��� �� ������ => ���ڰ��� ��������
        				idcrement=str.replaceAll("[^0-9]", "");
        				// �ѱۻ��� �� ������ => �ѱ۰��� ��������
        				state=str.replaceAll("[^��-�R]", "");

        			}
        			System.out.println("����:"+state);
        			System.out.println("����:"+idcrement);
        			// System.out.println("������Ű :"+youtubeKeyData(title.get(j).text()));
        			System.out.println("-------------------------------");
        			
        			MusicVO vo = new MusicVO();
        			vo.setMno(k);
        			vo.setTitle(title.get(j).text());
        			vo.setSinger(singer.get(j).text());
        			vo.setAlbum(album.get(j).text());
        			vo.setPoster(poster.get(j).attr("src"));
        			vo.setState(state);
        			vo.setIdcrement(Integer.parseInt(idcrement));
        			vo.setKey(youtubeKeyData(title.get(j).text()));
        				
        			dao.musicInsert(vo); // ���پ� �ö󰡰�
        			
        			Thread.sleep(100);
        			
        			k++;
  
        		}
				}catch(Exception ex) {}

			}
		} catch (Exception e) {
			
		}
		
		// ��Ʃ�� ȣ�� �ߵǴ��� �Ѱ��� Ȯ���غ���
		// youtubeKeyData("�ٽ� ���� �ٴ尡");
		

	}
	
	public static String youtubeKeyData(String title)
	{
		String key="";
		try {
			Document doc = Jsoup.connect("https://www.youtube.com/results?search_query="+title).get();
			Pattern p = Pattern.compile("/watch\\?v=[^��-Ş]+");
			// ������ doc�� ��ġ��Ų��.
			Matcher m = p.matcher(doc.toString());
			while(m.find())
			{
				// System.out.println(m.group());
				String str=m.group();
				
				// /watch?v=ESKfHHtiSjs","webPageType":"WEB_PAGE_TYPE_WATCH","rootVe":3832}},"watchEndpoint":{"videoId":"ESKfHHtiSjs"}},"badges":[{"metadataBadgeRenderer":{"style":"BADGE_STYLE_TYPE_SIMPLE","label":"
				// ���� = �޺κк��� "������ �߶����
				str=str.substring(str.indexOf("=")+1,str.indexOf("\""));
				key=str;
				break;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return key;
	}

}
