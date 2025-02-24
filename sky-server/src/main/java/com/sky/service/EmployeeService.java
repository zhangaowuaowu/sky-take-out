package com.sky.service;

import com.sky.dto.EmployeeDTO;
import com.sky.dto.EmployeeLoginDTO;
import com.sky.dto.EmployeePageQueryDTO;
import com.sky.entity.Employee;
import com.sky.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 新增員工
     *
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-19 17:49:18 Version:1.0
     * change Description:
     */

    void save(EmployeeDTO employeeDTO);

    /**
     * 員工列表
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date:  Version:1.0
     * change Description:
     */
    PageResult pageQuery(EmployeePageQueryDTO dto);

    /**
     * 啓用/禁用員工
     *
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-24 17:25:31 Version:1.0
     * change Description:
     */
    void startOrStop(Integer status, Long id);

    /**
     * 根据id查询员工
     *
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-24 17:42:47 Version:1.0
     * change Description:
     */
    Employee selectById(Long id);

    /**
     * 更新員工資料
     *
     * @param
     * @return
     * @author 刁卓
     * Change History:
     * Last Modify author :刁卓 Date: 2025-02-24 17:55:34 Version:1.0
     * change Description:
     */
    void updateEmployee(Employee employee);
}
