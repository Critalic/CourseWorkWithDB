<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css" rel="stylesheet">
    <title>Lot Editing</title>
</head>
<body>
<div class="bg-gradient-to-r from-yellow-400 via-red-500 to-pink-500 ...">
    <div class="relative py-3 sm:max-w-xl sm:mx-auto">
        <div class="absolute inset-0 bg-gradient-to-r from-cyan-400 to-light-blue-500 shadow-lg transform -skew-y-6 sm:skew-y-0 sm:-rotate-6 sm:rounded-3xl"></div>
        <div class="relative px-4 py-10 bg-white shadow-lg sm:rounded-3xl sm:p-20">
            <div class="max-w-md mx-auto">
                <div class="divide-y divide-gray-200">
                    <div class="py-8 text-base leading-6 space-y-4 text-gray-700 sm:text-lg sm:leading-7">
                        <p class="text-xl"><b>Name: </b>${lot.getName()}</p>
                        <p class="text-xl"><b>Description: </b></p>
                        <p class="text-base">${lot.getDescription()}</p>
                        <p class="test-xl"><b>Current value: </b> ${lot.getStartPrice()} $</p>
                        <p class="test-xl"><b>Is active: </b> ${lot.getIsActive()} </p>
                        <p class="test-xl"><b>Bids: </b></p>
                        <c:if test="${lotOffers.size()!=0}">
                            <table class="border-separate border border-green-800 ...">
                                <thead>
                                <tr>
                                    <th class="border border-green-600 ...">Bidder</th>
                                    <th class="border border-green-600 ...">Info</th>
                                    <th class="border border-green-600 ...">Offer</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach var="offer" items="${lotOffers}">
                                    <tr>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${offer.getOfferer().getName()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${offer.getDescription()}"/></td>
                                        <td class="border border-green-600 ..."><c:out
                                                value="${offer.getSuggestedPrice()} $"/></td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:if>
                        <c:if test="${lotOffers.size()==0}"><p class="test-base">No bids
                            yet</p></c:if>
                        <ul class="list-disc space-y-2">
                            <form action="${pageContext.request.contextPath}/lots/deleteLot?lotId=${lotId}"
                                  method="post">
                                <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                        type="submit">Delete this lot
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/lots/changeStatus?lotId=${lotId}"
                                  method="post">
                                <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                        type="submit">Change status
                                </button>
                            </form>
                            <form action="${pageContext.request.contextPath}/lots/generateURLForLot?lotId=${lotId}"
                                  method="get">
                                <button class="bg-blue-300 text-x1 font-semibold px-4 py-1 rounded hover:bg-blue-800 hover:text-white "
                                        type="submit">Generate URL
                                </button>
                            </form>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
