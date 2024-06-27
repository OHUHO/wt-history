<template>
	<div class="login-wrapper">
		<header class="rn-header header-default">
			<!--<div class="app-name">-->
				<h5 class="title w-600 theme-gradient">中国历史平台</h5>
			<!--</div>-->
			<div id="my_switcher" class="my_switcher">
				<ul>
					<li>
						<a href="javascript: void(0);"
						   @click.prevent="AppFunctions.toggleClass('body', 'active-light-mode')">
							<img class="light-icon"
							     src="../assets/images/icons/sun-01.svg"
							     alt="Sun images">
							<img class="dark-icon"
							     src="../assets/images/icons/vector.svg"
							     alt="Moon Images">
						</a>
					</li>
				</ul>
			</div>
		</header>
		
		<div class="rn-comment-form pt--60">
			<div class="inner">
				<div class="section-title">
					<h3 class="title login-title">登录</h3>
				</div>
				<form class="mt--40" action="#">
					<div class="row text-center">
						<div class="col-lg-12 login-form">
							<div class="rnform-group login-input">
								<input type="text"
								       required
								       v-model="loginForm.phoneNumber"
								       @blur="phoneCheck"
								       placeholder="请输入十一位手机号码">
								<div v-if="phoneInfo != ''" class="info">{{phoneInfo}}</div>
							</div>
							<div class="rnform-group login-input">
								<input class="password-input"
								       :type="isVisible? 'text':'password'"
								       required
								       v-model="loginForm.password"
								       @blur="passwordCheck"
								       placeholder="请输入密码">
								<div class="password-vi"
								     :class="[{'visible':isVisible}]"
								     @click="visible"></div>
								<div v-if="passwordInfo != ''" class="info">{{passwordInfo}}</div>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="blog-btn">
								<a class="btn-default login-btn" href="#" @click.prevent="login">
									<span>登录</span>
								</a>
							</div>
						</div>
						<span class="agree">登录即代表您同意<a href="#">《中国历史平台用户协议》</a></span>
					</div>
				</form>
			</div>
		</div>
		
		<div class="other-info">
			<a href="/register">用户注册 &nbsp;&nbsp;</a>|<a href="#">&nbsp;&nbsp;安全中心</a>
		</div>
		
	</div>
</template>

<script>
import AppFunctions from '../helpers/AppFunctions'
import {Message} from "element-ui";
export default {
	name: "LoginPage",
	data() {
		return {
			AppFunctions,
			isVisible: false,
			phoneInfo: '',
			passwordInfo: '',
			loginForm:{
				phoneNumber:'',
				password:'',
			}
		}
	},
	computed:{
		phoneNumberStyle(){
			let reg = /^1[3456789]\d{9}$/
			return reg.test(this.loginForm.phoneNumber);
		},
	},
	methods:{
		visible(){
			this.isVisible = !this.isVisible
		},
		phoneCheck(){
			if (this.loginForm.phoneNumber == ""){
				this.phoneInfo  = '手机号不能为空！'
			}else{
				if (this.phoneNumberStyle){
					this.phoneInfo = ''
					//console.log(this.loginForm)
				}else{
					this.phoneInfo = "请输入正确格式的手机号！"
				}
			}
		},
		passwordCheck(){
			if(this.loginForm.password == ""){
				this.passwordInfo  = '密码不能为空！'
			}else{
				this.passwordInfo = ''
			}
		},
		
		login(){
			if (this.loginForm.phoneNumber != "" && this.loginForm.password != ""){
				const register = {
					username: this.loginForm.phoneNumber,
					password: this.loginForm.password
				};
				this.postRequest("/common/login", register).then(resp => {
					if (resp){
						// 存储用户token
						console.log(resp)
						const tokenStr = resp.obj.tokenHead + " " + resp.obj.token;
						window.sessionStorage.setItem('tokenStr',tokenStr);
						
						Message({
							type: 'success',
							message: resp.message,
							offset: 50
						})
						
						this.$router.replace("/")
					}
				})
			}
		
		}
		
	}
}
</script>

<style scoped>

</style>
