<template>
    <main class="page-wrapper">
        <Header/>

        <div class="main-content">
            <slot></slot>
        </div>

        <Footer/>
    </main>
</template>

<script>
    import Header from './header/Header'
    import Footer from './footer/Footer'

    export default {
        name: 'Layout',
        components: {Header, Footer},
    }
</script>
