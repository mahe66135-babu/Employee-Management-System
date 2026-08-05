import { useEffect, useState } from "react";
import { getMyPerformance } from "../../services/performanceService";
import EmployeeLayout from "../../components/layouts/EmployeeLayout";

function MyPerformance() {

    const [reviews, setReviews] = useState([]);

    useEffect(() => {

        loadPerformance();

    }, []);

    const loadPerformance = async () => {

        try {

            const response = await getMyPerformance();

            setReviews(response.data);

        } catch (error) {

            console.error(error);

        }

    };

    return (

        <EmployeeLayout>

            <div className="card shadow">

                <div className="card-header">

                    <h3>My Performance</h3>

                </div>

                <div className="card-body">

                    {reviews.length === 0 ? (

                        <h5 className="text-center">

                            No Reviews Found

                        </h5>

                    ) : (

                        reviews.map(review => (

                            <div
                                key={review.id}
                                className="border rounded p-3 mb-3"
                            >

                                <h4>

                                    {"★".repeat(review.rating)}

                                </h4>

                                <p>

                                    <strong>Remarks:</strong>

                                    {" "}

                                    {review.remarks}

                                </p>

                                <p>

                                    <strong>Review Date:</strong>

                                    {" "}

                                    {review.reviewDate}

                                </p>

                            </div>

                        ))

                    )}

                </div>

            </div>

        </EmployeeLayout>

    );

}

export default MyPerformance;