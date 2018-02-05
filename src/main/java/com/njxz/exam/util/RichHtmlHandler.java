package com.njxz.exam.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RichHtmlHandler {
	private Document doc=null;
	private String html;

	private String docSrcParent = "";
	private String docSrcLocationPrex = "";
	private String nextPartId;//必须和已生成的配置文件中的其他nextPartId相同   01D395FD.81B8E900

	private String handledDocBodyBlock;
	private List<String> docBase64BlockResults = new ArrayList<String>();
	private List<String> xmlImgRefs = new ArrayList<String>();
	
	
	public RichHtmlHandler(String html) {
		//Jsoup解析html类型的文件
		doc = Jsoup.parse(wrappHtml(html));
	}
	
	//将传进来的字符串包装成完整的html文件
	private String wrappHtml(String html){
		// 因为传递过来都是不完整的doc
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("<body>");
		sb.append(html);

		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
	
	//主要方法---处理传进来的html文件--主要处理图片
	public void handledHtml(HttpServletRequest request)
			throws IOException {
		Elements imags = doc.getElementsByTag("img");

		if (imags == null || imags.size() == 0) {
			return;
		}

		// 转换成word mht 能识别图片标签内容，去替换html中的图片标签

		for (Element item : imags) {
			// 把文件取出来
			String src = item.attr("src");
			String srcRealPath = src;
			
			//图片的实际地址
			String t=request.getSession().getServletContext().getRealPath("");
	        srcRealPath=t.substring(0, t.lastIndexOf('\\'))+src;
	        
	        
			File imageFile = new File(srcRealPath);
			//图片名称
			String imageFielShortName = imageFile.getName();
			//得到文件后缀
			String fileTypeName = srcRealPath.substring(srcRealPath.lastIndexOf(".")+1);

			//生成存储在ftl文件中的图片名称
			String docFileName = "image" + StringUtil.seqGenerate() + "."+ fileTypeName;
			//生成存储在ftl文件中的图片地址--依据此地址寻找图片的base64编码
			String srcLocationShortName = docSrcParent + "/" + docFileName;

			String styleAttr = item.attr("                                                                      style"); // 样式
			//高度
			String imagHeightStr=item.attr("height");;
			if(StringUtil.isEmpty(imagHeightStr)){
				imagHeightStr = getStyleAttrValue(styleAttr, "height");
			}
			//宽度
			String imagWidthStr=item.attr("width");;
			if(StringUtil.isEmpty(imagHeightStr)){
				imagWidthStr = getStyleAttrValue(styleAttr, "width");
			}
	
			imagHeightStr = imagHeightStr.replace("px", "");
			imagWidthStr = imagWidthStr.replace("px", "");
			if(StringUtil.isEmpty(imagHeightStr)){
				//去得到默认的文件高度
				imagHeightStr="0";
			}
			if(StringUtil.isEmpty(imagWidthStr)){
				imagWidthStr="0";
			}
			int imageHeight = Integer.parseInt(imagHeightStr);
			int imageWidth = Integer.parseInt(imagWidthStr);
			
			// 得到文件的word mht的body块
			String handledDocBodyBlock = ImageConverter.toDocBodyBlock(srcRealPath,
					imageFielShortName, imageHeight, imageWidth,styleAttr,
					srcLocationShortName);

			item.parent().append(handledDocBodyBlock);
			item.remove();
			// 去替换原生的html中的imag

			String base64Content = ImageConverter
					.imageToBase64(srcRealPath);
			String contextLoacation = docSrcLocationPrex + "/" + docSrcParent
					+ "/" + docFileName;

			String docBase64BlockResult =ImageConverter
					.generateImageBase64Block(contextLoacation,
							fileTypeName, base64Content,nextPartId);
			docBase64BlockResults.add(docBase64BlockResult);

			String imagXMLHref = "<o:File HRef=3D\"" + docFileName + "\"/>";
			xmlImgRefs.add(imagXMLHref);

		}

	}
	
	//得到图片的属性--高度，宽度
	private String getStyleAttrValue(String style, String attributeKey) {
		if (StringUtil.isEmpty(style)) {
			return "";
		}

		// 以";"分割
		String[] styleAttrValues = style.split(";");
		for (String item : styleAttrValues) {
			// 在以 ":"分割
			String[] keyValuePairs = item.split(":");
			if (attributeKey.equals(keyValuePairs[0])) {
				return keyValuePairs[1];
			}
		}

		return "";
	}
	
	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getDocSrcParent() {
		return docSrcParent;
	}

	public void setDocSrcParent(String docSrcParent) {
		this.docSrcParent = docSrcParent;
	}

	public String getDocSrcLocationPrex() {
		return docSrcLocationPrex;
	}

	public void setDocSrcLocationPrex(String docSrcLocationPrex) {
		this.docSrcLocationPrex = docSrcLocationPrex;
	}

	public String getNextPartId() {
		return nextPartId;
	}

	public void setNextPartId(String nextPartId) {
		this.nextPartId = nextPartId;
	}

	public String getHandledDocBodyBlock() {
		String raw= ImageConverter.string2ASCII(doc.getElementsByTag("body").html());
		return raw.replace("=3D", "=").replace("=", "=3D");
	}

	public void setHandledDocBodyBlock(String handledDocBodyBlock) {
		this.handledDocBodyBlock = handledDocBodyBlock;
	}

	public List<String> getDocBase64BlockResults() {
		return docBase64BlockResults;
	}

	public void setDocBase64BlockResults(List<String> docBase64BlockResults) {
		this.docBase64BlockResults = docBase64BlockResults;
	}

	public List<String> getXmlImgRefs() {
		return xmlImgRefs;
	}

	public void setXmlImgRefs(List<String> xmlImgRefs) {
		this.xmlImgRefs = xmlImgRefs;
	}
}
