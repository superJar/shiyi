import request from '@/utils/request'
import { praseStrEmpty } from "@/utils/shiyi";

// 查询用户列表
export function listDetails(query) {
  return request({
    url: '/system/details/list',
    method: 'get',
    params: query
  })
}

// 查询用户详细
export function getDetails(userId) {
  return request({
    url: '/system/details/echo/' + praseStrEmpty(userId),
    method: 'get'
  })
}

// 新增用户
export function addUser(data) {
  return request({
    url: '/system/member/addOrUpdate',
    method: 'post',
    data: data
  })
}

// 修改用户
export function updateDetails(data) {
  return request({
    url: '/system/details/update',
    method: 'post',
    data: data
  })
}

// 删除用户
export function delUser(userId) {
  return request({
    url: '/system/member/' + userId,
    method: 'delete'
  })
}

// 导出用户
export function exportUser(query) {
  return request({
    url: '/system/user/export',
    method: 'get',
    params: query
  })
}

// 用户密码重置
export function resetUserPwd(userId, password) {
  const data = {
    userId,
    password
  }
  return request({
    url: '/system/user/resetPwd',
    method: 'put',
    data: data
  })
}

// 用户状态修改
export function changeUserStatus(userId, status) {
  const data = {
    userId,
    status
  }
  return request({
    url: '/system/user/changeStatus',
    method: 'put',
    data: data
  })
}

// 查询用户个人信息
export function getUserProfile() {
  return request({
    url: '/system/user/profile',
    method: 'get'
  })
}

// 修改用户个人信息
export function updateUserProfile(data) {
  return request({
    url: '/system/user/profile',
    method: 'put',
    data: data
  })
}

// 用户密码重置
export function updateUserPwd(oldPassword, newPassword) {
  const data = {
    oldPassword,
    newPassword
  }
  return request({
    url: '/system/user/profile/updatePwd',
    method: 'put',
    params: data
  })
}

// 用户头像上传
export function uploadAvatar(data) {
  return request({
    url: '/system/user/profile/avatar',
    method: 'post',
    data: data
  })
}

// 下载用户导入模板
export function importTemplate() {
  return request({
    url: '/system/user/importTemplate',
    method: 'get'
  })
}


// 充值
export function topUp(id, topUpAmount) {
  const data = {
    id,
    topUpAmount
  }
  return request({
    url: '/system/member/topUp',
    method: 'put',
    data: data
  })
}

// 消费
export function consume(id, sumOfExpenditure) {
  const data = {
    id,
    sumOfExpenditure
  }
  return request({
    url: '/system/member/deduction',
    method: 'put',
    data: data
  })
}

//增加备注
export function updateDetail(id,remark) {
  const data = {
    id,
    remark
  }
  return request({
    url: '/system/details/update',
    method: 'post',
    data: data
  })
}
