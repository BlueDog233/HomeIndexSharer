package cn.bluedog2333.blueorginbackinit.typehandlers;

import cn.bluedog2333.blueorginbackinit.model.pojo.Info;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.*;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@MappedJdbcTypes(JdbcType.VARCHAR)
@MappedTypes(List.class)
public class JsonArrayTypeHandler extends BaseTypeHandler<List<Info>> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, List<Info> parameter, JdbcType jdbcType) throws SQLException {
        try {
            String json = objectMapper.writeValueAsString(parameter);
            ps.setString(i, json);
        } catch (JsonProcessingException e) {
            throw new SQLException("Error converting List<Info> to JSON", e);
        }
    }

    @Override
    public List<Info> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return fromJson(rs.getString(columnName));
    }

    @Override
    public List<Info> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return fromJson(rs.getString(columnIndex));
    }

    @Override
    public List<Info> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return fromJson(cs.getString(columnIndex));
    }

    private List<Info> fromJson(String json) throws SQLException {
        if (json == null || json.trim().isEmpty()) {
            return null;
        }
        return JSONUtil.parseArray(json).toList(Info.class);
    }
}
