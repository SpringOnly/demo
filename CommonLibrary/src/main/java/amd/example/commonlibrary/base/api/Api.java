package amd.example.commonlibrary.base.api;


import amd.example.commonlibrary.base.BaseApi;

public class Api {
    private static ApiService apiService;

    public static ApiService getService() {
        if (apiService == null) {
            synchronized (Api.class) {
                if (null == apiService) {
                    apiService = BaseApi.getInstance().getRetrofit().create(ApiService.class);
                }
            }
        }
        return apiService;
    }

    public static void setNullApiService() {
        apiService = null;
    }
}
