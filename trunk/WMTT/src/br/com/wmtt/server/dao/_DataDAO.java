package br.com.wmtt.server.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;

import br.com.wmtt.server.util.DataUtil;
import br.com.wmtt.shared.model._Data;

public class _DataDAO {
	
	public static int addData(_Data d) {
		
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo")); 
		
		PreparedStatement stmt;
		String sql = "insert into datas(data, hr_ini, hr_fim) values (?,?,?)";
		int id = 0;	
		try {
			Connection con = _ConnectionMannager.getConnetion();
			stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			System.out.println("###inserindo datas:");
			System.out.println("###Data:" + d.getData());
			System.out.println("###Hora ini:"+ d.getHrInicio());
			System.out.println("###Hora fim:"+ d.getHrFim());
			stmt.setDate(1, DataUtil.formatDateToSave(d.getData()));
			stmt.setTime(2, DataUtil.formatTimeToSave(d.getHrInicio()));
			stmt.setTime(3, DataUtil.formatTimeToSave(d.getHrFim()));
			
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys(); 
			 if(rs.next()){  
			        id = rs.getInt(1); 
			    }  
			stmt.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
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
				
				System.out.println("###Pegando datas:");
				System.out.println("###Data:" + data.getData());
				System.out.println("###Hora ini:"+ data.getHrInicio());
				System.out.println("###Hora fim:"+ data.getHrFim());
							
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
