<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Sign UP</title>
</head>
<body>
<c:if test="${errorMessage != null}">
    <script>
        alert("${errorMessage}")
    </script>
</c:if>
<div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
        <div class="relative py-3 sm:max-w-xl sm:mx-auto">
            <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
            <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
                <div class="max-w-md mx-auto">
                    <div class="divide-y divide-gray-200">
                        <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                            <p><h1><b>Great!</p> <p>Please fill in the following fields</p></b></h1>
                            <form action="${pageContext.request.contextPath}/lots/signUp" method="post">
                                <ul class="list-disc space-y-2">
                                    <li> What's your name?
                                        <div class="mb-3 pt-0">
                                            <input type="text" placeholder="Don't use numbers here" name = "name" class="px-3 py-3 placeholder-blueGray-300 text-blueGray-600 relative bg-white bg-white rounded text-sm border-0 shadow outline-none focus:outline-none focus:ring w-full"/>
                                        </div>
                                    </li>
                                    <li> What's your e-mail?
                                        <div class="mb-3 pt-0">
                                            <input type="text" name = "email" class="px-3 py-3 placeholder-blueGray-300 text-blueGray-600 relative bg-white bg-white rounded text-sm border-0 shadow outline-none focus:outline-none focus:ring w-full"/>
                                        </div>
                                    </li>
                                    <li> Enter your password
                                        <div class="mb-3 pt-0">
                                            <input type="password"  name = "password1" class="px-3 py-3 placeholder-blueGray-300 text-blueGray-600 relative bg-white bg-white rounded text-sm border-0 shadow outline-none focus:outline-none focus:ring w-full"/>
                                        </div>
                                    </li>
                                    <li> Re-enter your password
                                        <div class="mb-3 pt-0">
                                            <input type="password"  name = "password2" class="px-3 py-3 placeholder-blueGray-300 text-blueGray-600 relative bg-white bg-white rounded text-sm border-0 shadow outline-none focus:outline-none focus:ring w-full"/>
                                        </div>
                                    </li>
                                </ul>
                                <button class = "bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white " type="submit">Sign up</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
