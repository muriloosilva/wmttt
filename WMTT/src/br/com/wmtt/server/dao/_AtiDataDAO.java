package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import br.com.wmtt.server.util.DataUtil;
import br.com.wmtt.shared.model._Data;

public class _AtiDataDAO {
	
	public static void addAtiData(int idData, int idAtividade) {
		PreparedStatement stmt;
		String sql = "insert into ati_data(fk_id_data, fk_id_ativid) values (?,?)";
		try {
			Connection con = _ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, idData);
			stmt.setInt(2, idAtividade);
			
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static List<_Data> getData(int idAtivi) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); 
		
//		System.out.println("id: "+idAtivi);
		PreparedStatement stmt;
		List<_Data> datas = new ArrayList<_Data>();
		try {
			Connection con = _ConnectionMannager.getConnetion();
			stmt = con
					.prepareStatement("select data, hr_ini, hr_fim from datas d inner join ati_data ad on "
							+ "d.id_data = ad.fk_id_data where ad.fk_id_ativid= "+idAtivi+" order by data, hr_ini");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				_Data data = new _Data();
				
				data.setData(rs.getDate(1).toString());
				data.setHrInicio(DataUtil.formatTimeShow(rs.getTime(2)).toString());
				data.setHrFim(DataUtil.formatTimeShow(rs.getTime(3)).toString());
							
				datas.add(data);
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (datas.isEmpty())
			return null;
		else
			return datas;
	}
}
