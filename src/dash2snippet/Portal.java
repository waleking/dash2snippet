package dash2snippet;

import java.util.ArrayList;
import java.util.List;

import tool.SnippetMappingFunction;
import db.dash.DashSnippetsDAO;
import db.dash.DashSnippetsVO;
import db.snippet.LabelDAO;
import db.snippet.SnippetDAO;
import db.snippet.SnippetLabelsDAO;
import db.snippet.SnippetVO;

public class Portal {

	private static LabelDAO snippetLabelDAO = new LabelDAO();
	private static SnippetDAO snippetSnippetDAO = new SnippetDAO();
	private static SnippetLabelsDAO snippetSnippetLabelsDAO = new SnippetLabelsDAO();
	private static DashSnippetsDAO dashSnippetsDAO = new DashSnippetsDAO();

	public static void cleanSnippet() {
		// delete from snippet.label
		snippetLabelDAO.deleteAll();
		// delete from snippet.snippet
		snippetSnippetDAO.deleteAll();
		// delete from snippet.snippetLabels
		snippetSnippetLabelsDAO.deleteAll();
	}

	public static void main(String[] args) {
		long start = System.currentTimeMillis();

		System.out.println("clean snippet db");
		cleanSnippet();

		// read from dash.snippet, 
		List<SnippetVO> lSnippet=new ArrayList<SnippetVO>();
		List<DashSnippetsVO> lDashSnippet = dashSnippetsDAO.scan();
		for (int i = 0; i < lDashSnippet.size(); i++) {
			DashSnippetsVO dashSnippetsVO = lDashSnippet.get(i);
			// System.out.println(dashSnippetsVO);
			SnippetVO snippetVO = new SnippetVO();
			snippetVO.setKey(SnippetMappingFunction
					.turnIntToString22(dashSnippetsVO.getSid()));
			snippetVO.setName(dashSnippetsVO.getTitle());
			snippetVO.setSourceCode(dashSnippetsVO.getBody());
			lSnippet.add(snippetVO);
		}
		System.out.println("insert "+lSnippet.size()+"snippets into snippet db batchly");
		//insert into snippet.snippet batchly
		snippetSnippetDAO.insertBatchly(lSnippet);
		
		long end=System.currentTimeMillis();
		System.out.println("finish in "+(end-start)+" milliseconds");

		/*
		 * // read from dash.tag, and insert into snippet.label List<DashTagVO>
		 * lDashTag = dashTagDAO.scan(); for (DashTagVO dashTagVO : lDashTag) {
		 * System.out.println(dashTagVO); LabelVO snippetLabelVO = new
		 * LabelVO(); snippetLabelVO.setKey(SnippetMappingFunction
		 * .turnIntToString22(dashTagVO.getTid()));
		 * snippetLabelVO.setName(dashTagVO.getTag());
		 * snippetLabelDAO.insert(snippetLabelVO); }
		 * 
		 * // read from dash.tagsIndex, and insert into snippet.snippetLabels
		 * List<DashTagsIndexVO> lDashTagsIndex = dashTagsIndexDAO.scan(); for
		 * (DashTagsIndexVO dashTagsIndexVO : lDashTagsIndex) {
		 * System.out.println(dashTagsIndexVO); SnippetLabelsVO snippetLabelsVO
		 * = new SnippetLabelsVO();
		 * snippetLabelsVO.setLabelKey(SnippetMappingFunction
		 * .turnIntToString22(dashTagsIndexVO.getTid()));
		 * snippetLabelsVO.setSnippetKey(SnippetMappingFunction
		 * .turnIntToString22(dashTagsIndexVO.getSid()));
		 * snippetSnippetLabelsDAO.insert(snippetLabelsVO); }
		 */

	}
}
