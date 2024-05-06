package cn.bluedog2333.blueorginbackinit.typehandlers;

import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(ArrayList.class)
public class JsonArrayIntegerTypeHandler extends BaseTypeHandler<ArrayList<Integer>> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, ArrayList<Integer> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting ArrayList<Integer> to JSON", e);
        }
    }

    @Override
    public ArrayList<Integer> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public ArrayList<Integer> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public ArrayList<Integer> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private ArrayList<Integer> fromJson(String json) throws SQLException {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        ArrayList<Integer> arr=new ArrayList();
        JSONUtil.parseArray(json).toList(Integer.class).forEach(x->arr.add(x));
        return arr;
    }
}
