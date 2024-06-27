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
					<h3 class="title login-title">注册</h3>
					<h5>已有账号，去 <a class="re-to-lo" href="/login">登录></a></h5>
				</div>
				<form class="mt--40" action="#">
					<div class="row text-center">
						<div class="col-lg-12 login-form">
							<div class="rnform-group login-input">
								<input type="text"
								       required
								       v-model="registerForm.phoneNumber"
								       @blur="phoneCheck"
								       placeholder="请输入十一位手机号码">
								<div v-if="phoneInfo != ''" class="info">{{phoneInfo}}</div>
							</div>
							<div class="rnform-group login-input">
								<input class="password-input"
								       :type="isVisible? 'text':'password'"
								       required
								       v-model="registerForm.password"
								       @blur="passwordCheck"
								       placeholder="请输入密码">
								<div class="password-vi"
								     :class="[{'visible':isVisible}]"
								     @click="visible"></div>
								<div v-if="passwordInfo != ''" class="info">{{passwordInfo}}</div>
							</div>
							<div class="rnform-group login-input">
								<input class="password-input"
								       :type="isVisible? 'text':'password'"
								       required
								       v-model="registerForm.rePassword"
								       @blur="rePasswordCheck"
								       placeholder="请确认密码">
								<div class="password-vi"
								     :class="[{'visible':isVisible}]"
								     @click="visible"></div>
								<div v-if="rePasswordInfo != ''" class="info">{{rePasswordInfo}}</div>
							</div>
						</div>
						<div class="col-lg-12">
							<div class="blog-btn">
								<a class="btn-default login-btn" href="#"  @click.prevent="register">
									<span>注册</span>
								</a>
							</div>
						</div>
						<span class="agree">登录即代表您同意<a href="#">《中国历史平台用户协议》</a></span>
					</div>
				</form>
			</div>
		</div>
	
	</div>
</template>

<script>
import AppFunctions from '../helpers/AppFunctions'
import {Message} from "element-ui";
export default {
	name: "RegisterPage",
	data() {
		return {
			AppFunctions,
			isVisible: false,
			phoneInfo: '',
			passwordInfo: '',
			rePasswordInfo:'',
			registerForm:{
				phoneNumber:'',
				password:'',
				rePassword:'',
			}
		}
	},
	computed:{
		phoneNumberStyle(){
			let reg = /^1[3456789]\d{9}$/
			return reg.test(this.registerForm.phoneNumber);
		},
	},
	methods:{
		visible(){
			this.isVisible = !this.isVisible
		},
		phoneCheck(){
			if (this.registerForm.phoneNumber == ""){
				this.phoneInfo  = '手机号不能为空！'
			}else{
				if (this.phoneNumberStyle){
					this.phoneInfo = ''
				}else{
					this.phoneInfo = "请输入正确格式的手机号！"
				}
			}
		},
		passwordCheck(){
			if(this.registerForm.password == ""){
				this.passwordInfo  = '密码不能为空！'
			}else{
				this.passwordInfo = ''
			}
		},
		rePasswordCheck(){
			if(this.registerForm.rePassword == ""){
				this.rePasswordInfo  = '密码不能为空！'
			}else{
				if (this.registerForm.password != this.registerForm.rePassword){
					this.rePasswordInfo = '两次密码不一致！'
				}else{
					this.rePasswordInfo = ''
				}
			}
		},
		
		register(){
			if(this.registerForm.phoneNumber != "" && this.registerForm.password != "" &&
				this.registerForm.password == this.registerForm.rePassword){
				const register = {
					username: this.registerForm.phoneNumber,
					password: this.registerForm.password
				};
				// 发送注册请求
				this.postRequest('/common/register', register).then(resp => {
					if (resp){
						Message({
							type: 'success',
							message: resp.message,
							offset: 50
						})
						this.$router.replace('/login')
					}
				})
			}
		
		}
		
	}
}
</script>

<style scoped>

</style>
