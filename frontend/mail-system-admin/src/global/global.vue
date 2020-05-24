<template>

</template>

<script>
    export default {
        name: "global",
        httpUrl,
        commonFun,
        setToken
    }
    const httpUrl = 'http://localhost:8080/api/v1/';
    const token = '';
    function commonFun() {
        console.log("公共方法")
    }
    function setToken(token) {
        this.token = token;
    }
</script>

<style scoped>

</style>
