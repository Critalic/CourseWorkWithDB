<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="text/javascript" src="../JS/EmptyFormValidator.js"></script>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Search lot</title>
</head>
<body>
<c:if test="${lotsFound==null}">
    <div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
        <div class="relative py-3 sm:max-w-xl sm:mx-auto">
            <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
            <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
                <div class="max-w-md mx-auto">
                    <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                        <p>
                        <h1><b>Great!</p> <p>Find the lot you're interested in by filling the
                            following form</p></b></h1>
                        <form method="post"
                              action="${pageContext.request.contextPath}/lots/searchLot">
                            <ul class="list-disc space-y-2">
                                <li> Type in the lot's name or information about it
                                    <div class="mb-3 pt-0">
                                        <input required type="text" name="input"
                                               class="px-3 py-3 placeholder-blueGray-300 text-blueGray-600 relative bg-white bg-white rounded text-sm border-0 shadow outline-none focus:outline-none focus:ring w-full"/>
                                    </div>
                                </li>
                                <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                        type="submit">Search
                                </button>
                            </ul>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
<c:if test="${lotsFound!=null}">
    <div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
        <div class="relative py-3 sm:max-w-xl sm:mx-auto">
            <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
            <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
                <div class="max-w-md mx-auto">
                    <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                        <c:if test="${lotsFound.size()==0}">
                            <p>
                            <h1><b>Sorry</p> <p>No lots were found. Try again</p></b></h1>
                            <form method="post"
                                  action="${pageContext.request.contextPath}/lots/searchLot">
                                <ul class="list-disc space-y-2">
                                    <li> Type in the lot's name or information about it
                                        <div class="mb-3 pt-0">
                                            <input required type="text" name="input"
                                                   class="px-3 py-3 placeholder-blueGray-300 text-blueGray-600 relative bg-white bg-white rounded text-sm border-0 shadow outline-none focus:outline-none focus:ring w-full"/>
                                        </div>
                                    </li>
                                    <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                            type="submit">Search
                                    </button>
                                </ul>
                            </form>
                        </c:if>
                        <c:if test="${lotsFound.size()!=0}">
                            <p>
                            <h1><b>Great!</p> <p>Here are the lots we've found</p></b></h1>
                            <c:if test="${user==null}">
                                <table class="border-separate border border-green-800 ...">
                                    <thead>
                                    <tr>
                                        <th class="border border-green-600 ...">Lot name</th>
                                        <th class="border border-green-600 ...">Info</th>
                                        <th class="border border-green-600 ...">Owner</th>
                                        <th class="border border-green-600 ...">Last offered price
                                        </th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="lot" items="${lotsFound}">
                                        <c:if test="${lot.getIsActive()}">
                                            <tr>
                                                <td class="border border-green-600 ..."><c:out
                                                        value="${lot.getName()}"/></td>
                                                <td class="border border-green-600 ..."><c:out
                                                        value="${lot.getDescription()}"/></td>
                                                <td class="border border-green-600 ..."><c:out
                                                        value="${lot.getCustomer().getName()}"/></td>
                                                    <%--                                            <td class="border border-green-600 ..."><c:out value="${lot.getOffersQuantity}"/></td>--%>
                                                <td class="border border-green-600 ..."><c:out
                                                        value="${lot.getStartPrice()} $"/></td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                            <c:if test="${user!=null}">
                                <form class="max-w-md mx-auto" method="get"
                                      action="${pageContext.request.contextPath}/lots/viewLot">
                                    <table class="border-separate border border-green-800 ...">
                                        <thead>
                                        <tr>
                                            <th class="border border-green-600 ...">Lot name</th>
                                            <th class="border border-green-600 ...">Info</th>
                                            <th class="border border-green-600 ...">Owner</th>
                                            <th class="border border-green-600 ...">Last offered
                                                price
                                            </th>
                                            <th class="border border-green-600 ...">Select to view
                                            </th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="lot" items="${lotsFound}">
                                            <c:if test="${lot.getIsActive()}">
                                                <tr>
                                                    <td class="border border-green-600 ..."><c:out
                                                            value="${lot.getName()}"/></td>
                                                    <td class="border border-green-600 ..."><c:out
                                                            value="${lot.getDescription()}"/></td>
                                                    <td class="border border-green-600 ..."><c:out
                                                            value="${lot.getCustomer().getName()}"/></td>
                                                        <%--                                                <td class="border border-green-600 ..."><c:out value="${lot.getOffersQuantity}"/></td>--%>
                                                    <td class="border border-green-600 ..."><c:out
                                                            value="${lot.getStartPrice()} $"/></td>
                                                    <td class="border border-green-600 ...">
                                                        <input type="radio" id="myCheckbox"
                                                               name="lotID" value="${lot.getId()}"/>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                        </tbody>
                                    </table>

                                    <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                            type="submit">View
                                    </button>
                                </form>
                            </c:if>
                            <form method="get"
                                  action="${pageContext.request.contextPath}/lots/searchLot">
                                <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                        type="submit">Search again
                                </button>
                            </form>
                            <form method="get"
                                  action="${pageContext.request.contextPath}/lots/default">
                                <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                        type="submit">View all lots
                                </button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</c:if>
</body>
</html>
